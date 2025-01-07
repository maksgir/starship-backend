package com.chomik.service

import jakarta.ejb.Remote

@Remote
interface UserService {
    fun ping(): String
}
