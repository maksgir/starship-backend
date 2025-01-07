package com.chomik.service.impl

import com.chomik.service.UserService
import jakarta.ejb.Stateless
import org.jboss.ejb3.annotation.Pool

@Stateless
@Pool("space-marines-pool")
class UserServiceImpl : UserService {
    override fun ping() = "pong"
}
