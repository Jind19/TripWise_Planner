package com.tripwise.tripplanner.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.*;
import java.util.stream.Collectors;

public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final String rolesClaim;
    public JwtRoleConverter(String rolesClaim){ this.rolesClaim = rolesClaim; }


    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        return extractRoles(jwt.getClaims()).stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toUnmodifiableSet());
    }


    @SuppressWarnings("unchecked")
    static Set<String> extractRoles(Map<String, Object> claims){
// 1) plain roles array
        Object roles = claims.get("roles");
        if (roles instanceof Collection<?> c) {
            return c.stream().map(Object::toString).collect(Collectors.toSet());
        }
// 2) configurable claim name
        Object cfg = claims.get("authorities");
        if (cfg instanceof Collection<?> c2) {
            return c2.stream().map(Object::toString).collect(Collectors.toSet());
        }
// 3) Keycloak-style realm_access.roles
        Object realm = claims.get("realm_access");
        if (realm instanceof Map<?,?> m) {
            Object rr = m.get("roles");
            if (rr instanceof Collection<?> c3) {
                return c3.stream().map(Object::toString).collect(Collectors.toSet());
            }
        }
// 4) scopes as roles fallback
        Object scope = claims.getOrDefault("scope", claims.get("scp"));
        if (scope instanceof String s) {
            return new HashSet<>(Arrays.asList(s.split(" ")));
        }
        return Collections.emptySet();
    }
}