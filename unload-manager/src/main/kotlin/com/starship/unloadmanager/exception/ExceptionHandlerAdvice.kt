package com.starship.unloadmanager.exception

import com.starship.unloadmanager.dto.BadParamsResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(BadRequestException::class)
    fun handleException(e: BadRequestException): ResponseEntity<BadParamsResponseDto> {
        return ResponseEntity.status(e.httpStatus).body(e.badParamsResponseDto)
    }

    @ExceptionHandler(ResourceException::class)
    fun handleException(e: ResourceException): ResponseEntity<String> {
        return ResponseEntity.status(e.httpStatus).body(e.message)
    }
}
