package com.tripwise.tripplanner.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public final class SecurityUtils {
    private SecurityUtils() {}

    public static String currentUserId() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object p = a.getPrincipal();
        if (p instanceof Jwt jwt) return jwt.getSubject(); // Google 'sub'
        return a.getName();
    }

    public static String currentEmail() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object p = a.getPrincipal();
        if (p instanceof Jwt jwt) return jwt.getClaimAsString("email");
        return null;
    }

    public static String currentTokenValue() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) return null;
        Object p = a.getPrincipal();
        if (p instanceof Jwt jwt) return jwt.getTokenValue();
        return null;
    }
}

