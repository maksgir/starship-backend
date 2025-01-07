package com.chomik.util

import com.chomik.domain.dto.ErrorResponseDto
import jakarta.ws.rs.core.Response

fun String.camelToSnake(): String {
    var result = ""

    val c: Char = this[0]
    result += c.lowercaseChar()

    for (i in 1 until this.length) {
        val ch: Char = this[i]

        if (Character.isUpperCase(ch)) {
            result += '_'
            result = (result + ch.lowercaseChar())
        } else {
            result += ch
        }
    }

    return result
}

fun String.snakeToCamel(): String {
    return this.split('_')
        .joinToString("") { it.lowercase().replaceFirstChar { char -> char.uppercase() } }
        .replaceFirstChar { it.lowercase() }
}

fun Exception.buildBadRequestResponse(): Response = Response
    .status(Response.Status.BAD_REQUEST)
    .entity(
        ErrorResponseDto(
            code = Response.Status.BAD_REQUEST.statusCode,
            message = this.message ?: this.stackTraceToString()
        )
    )
    .build()
