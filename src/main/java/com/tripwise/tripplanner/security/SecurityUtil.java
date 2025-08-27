package com.tripwise.tripplanner.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;


public final class SecurityUtil {
    private SecurityUtil() {
    }

    @SuppressWarnings("unchecked")
    public static UserPrincipal currentUser() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object principal = a.getPrincipal();
        if (principal instanceof Jwt jwt) {
            String sub = jwt.getSubject();
            String email = (String) jwt.getClaims().getOrDefault("email", a.getName());
            String name = (String) jwt.getClaims().getOrDefault("name", email);
            Set<String> roles = JwtRoleConverter.extractRoles(jwt.getClaims());
            return new UserPrincipal(sub, email, name, roles);
        }
        return null;
    }
}
