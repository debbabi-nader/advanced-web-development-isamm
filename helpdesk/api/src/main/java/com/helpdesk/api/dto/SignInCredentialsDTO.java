package com.helpdesk.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.helpdesk.api.utils.validation.constraints.EmailConstraint;


public final class SignInCredentialsDTO implements Serializable {

    private static final long serialVersionUID = 6135312062188277856L;

    @NotNull
    @EmailConstraint
    private final String email;

    @NotBlank
    private final String password;

    public SignInCredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

}
