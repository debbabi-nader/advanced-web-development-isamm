package com.helpdesk.api.exceptions;


public class JWTDecodingException extends RuntimeException {

    private static final long serialVersionUID = 5021042809101444056L;

    public JWTDecodingException(String message, Throwable cause) {
        super(message, cause);
    }

}
