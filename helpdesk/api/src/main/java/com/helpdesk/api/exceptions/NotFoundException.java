package com.helpdesk.api.exceptions;


public abstract class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4003187797072492377L;

    protected NotFoundException() {
        super();
    }

    protected NotFoundException(String message) {
        super(message);
    }

    protected NotFoundException(Throwable cause) {
        super(cause);
    }

}
