package com.starship.unloadmanager.exception;

import com.starship.unloadmanager.dto.BadParamsResponseDto;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ResourceException {
    private final BadParamsResponseDto badParamsResponseDto;

    public BadRequestException(BadParamsResponseDto badParamsResponseDto) {
        super(HttpStatus.BAD_REQUEST);
        this.badParamsResponseDto = badParamsResponseDto;
    }

    public BadParamsResponseDto getBadParamsResponseDto() {
        return badParamsResponseDto;
    }
}
