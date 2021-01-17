package com.helpdesk.api.dto;

import java.io.Serializable;


public final class AuthenticationTokenDTO implements Serializable {

    private static final long serialVersionUID = 3182354681004422446L;

    private final String token;

    public AuthenticationTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
