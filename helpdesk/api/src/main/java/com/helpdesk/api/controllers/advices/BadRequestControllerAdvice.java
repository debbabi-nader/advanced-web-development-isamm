package com.helpdesk.api.controllers.advices;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.api.dto.ErrorResponseDTO;


@ControllerAdvice
public class BadRequestControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestControllerAdvice.class);

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        LOGGER.error("handleMethodArgumentNotValidException is invoked...", methodArgumentNotValidException);

        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        "Some validation constraints are violated",
                        methodArgumentNotValidException.getMessage(),
                        Instant.now()),
                HttpStatus.BAD_REQUEST);

    }

}
