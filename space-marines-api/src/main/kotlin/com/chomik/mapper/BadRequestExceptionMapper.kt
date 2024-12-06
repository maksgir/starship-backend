package com.chomik.mapper

import jakarta.ws.rs.ProcessingException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class BadRequestExceptionMapper : ExceptionMapper<ProcessingException> {
    override fun toResponse(exception: ProcessingException): Response {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity("Error processing XML: ${exception.message}")
            .build()
    }
}