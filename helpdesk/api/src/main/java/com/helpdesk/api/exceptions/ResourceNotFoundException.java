package com.helpdesk.api.exceptions;


public class ResourceNotFoundException extends NotFoundException {

    private static final long serialVersionUID = -2105239003165681926L;

    private final String id;

    public ResourceNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
