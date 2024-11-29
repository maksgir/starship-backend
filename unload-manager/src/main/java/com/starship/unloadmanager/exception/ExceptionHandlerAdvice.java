package com.starship.unloadmanager.exception;

import com.starship.unloadmanager.dto.BadParamsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadParamsResponseDto> handleException(BadRequestException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getBadParamsResponseDto());
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<String> handleException(ResourceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
