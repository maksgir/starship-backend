package com.chomik.domain

class Excep(
    override val message: String,
    override val cause: Throwable
): Exception()
