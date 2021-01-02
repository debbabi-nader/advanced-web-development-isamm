package com.helpdesk.api.controllers.advices;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.api.dto.ErrorResponseDTO;
import com.helpdesk.api.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class NotFoundControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundControllerAdvice.class);

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {

        LOGGER.error("handleResourceNotFoundException is invoked...", resourceNotFoundException);

        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        HttpStatus.NOT_FOUND.value(),
                        "The requested record with id " + resourceNotFoundException.getId() + " was not found",
                        resourceNotFoundException.getMessage(),
                        Instant.now()),
                HttpStatus.NOT_FOUND);

    }

}
