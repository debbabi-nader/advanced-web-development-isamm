package com.helpdesk.api.dto;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public final class UserAuthenticationTokenDTO implements Authentication {

    private static final long serialVersionUID = -2333995042612889765L;

    private final String id;
    private final String principal;
    private final String email;
    private final String role;

    public UserAuthenticationTokenDTO(
            String id,
            String principal,
            String email,
            String role) {

        this.id = id;
        this.principal = principal;
        this.email = email;
        this.role = role;

    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(
                new SimpleGrantedAuthority(this.role));

    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return (Object) this.email;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        throw new UnsupportedOperationException();
    }

}
