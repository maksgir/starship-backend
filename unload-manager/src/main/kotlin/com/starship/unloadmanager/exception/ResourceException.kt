package com.starship.unloadmanager.exception

import org.springframework.http.HttpStatus

open class ResourceException : RuntimeException {
    val httpStatus: HttpStatus

    constructor(httpStatus: HttpStatus) {
        this.httpStatus = httpStatus
    }

    constructor(httpStatus: HttpStatus, message: String?) : super(message) {
        this.httpStatus = httpStatus
    }
}
