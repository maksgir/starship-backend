package com.chomik.service

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService {
    fun ping() = "pong"
}
