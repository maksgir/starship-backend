package com.chomik.controller

import com.chomik.service.UserService
import jakarta.ejb.EJB
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserController {

    @EJB
    private lateinit var userService: UserService

    @GET
    fun getUsers(): String {
        return userService.ping()
    }
}
