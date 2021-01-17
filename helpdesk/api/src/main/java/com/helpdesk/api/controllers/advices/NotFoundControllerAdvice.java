package com.helpdesk.api.controllers.advices;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.api.dto.ErrorResponseDTO;
import com.helpdesk.api.exceptions.AccountNotFoundException;
import com.helpdesk.api.exceptions.NotFoundException;
import com.helpdesk.api.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class NotFoundControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundControllerAdvice.class);

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            NotFoundException notFoundException) {

        LOGGER.error("handleResourceNotFoundException is invoked...", notFoundException);

        String message = "";

        if (notFoundException instanceof ResourceNotFoundException) {
            message = "The requested record with id " + ((ResourceNotFoundException) notFoundException).getId() + " was not found";
        } else if (notFoundException instanceof AccountNotFoundException) {
            message = "No account was found for the given email address: " + ((AccountNotFoundException) notFoundException).getEmail();
        }

        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        HttpStatus.NOT_FOUND.value(),
                        "Not found",
                        message,
                        Instant.now()),
                HttpStatus.NOT_FOUND);

    }

}
