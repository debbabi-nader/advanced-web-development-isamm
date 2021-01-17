package com.helpdesk.api.exceptions;


public class AccountNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1208614287249452122L;

    private final String email;

    public AccountNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

}
