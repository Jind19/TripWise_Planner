package com.tripwise.tripplanner.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthUtils {

    public static String userIdFromJwt(Jwt jwt) {
        if (jwt == null) return null;
        Object sub = jwt.getClaim("sub");
        return sub != null ? sub.toString() : jwt.getSubject();
    }

    // Extract userId from SecurityContext
    public static String userIdFromSecurityContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthToken) {
            Jwt jwt = jwtAuthToken.getToken();
            return userIdFromJwt(jwt);
        }
        return null;
    }
}


