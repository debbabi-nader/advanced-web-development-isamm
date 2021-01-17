package com.helpdesk.api.controllers.advices;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.api.dto.ErrorResponseDTO;
import com.helpdesk.api.exceptions.WrongPasswordException;


@ControllerAdvice
public class UnauthorizedControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedControllerAdvice.class);

    @ExceptionHandler(value = { WrongPasswordException.class })
    public ResponseEntity<ErrorResponseDTO> handleWrongPasswordException(
            WrongPasswordException wrongPasswordException) {

        LOGGER.error("handleWrongPasswordException is invoked...", wrongPasswordException);

        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        HttpStatus.UNAUTHORIZED.value(),
                        "Unauthorized",
                        "Wrong password",
                        Instant.now()),
                HttpStatus.UNAUTHORIZED);

    }

}
