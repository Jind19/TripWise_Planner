package com.tripwise.tripplanner.security;

import java.util.Set;

public record UserPrincipal(String sub, String email, String name, Set<String> roles) {
    public boolean isAdmin(){ return roles.contains("ADMIN"); }
}
