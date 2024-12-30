package com.starship.unloadmanager.exception

import com.starship.unloadmanager.dto.BadParamsResponseDto
import org.springframework.http.HttpStatus

class BadRequestException(val badParamsResponseDto: BadParamsResponseDto) : ResourceException(HttpStatus.BAD_REQUEST)
