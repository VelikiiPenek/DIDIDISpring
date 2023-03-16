package com.example.Salon.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,EMPLOYEE,ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }
}
