package com.helpdesk.api.dto;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public final class JWTAuthenticationTokenDTO implements Authentication {

    private static final long serialVersionUID = 5314891316942742832L;

    private final String jwt;

    public JWTAuthenticationTokenDTO(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated){
        throw new UnsupportedOperationException();
    }

}
