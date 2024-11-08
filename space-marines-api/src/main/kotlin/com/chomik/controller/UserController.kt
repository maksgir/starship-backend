package com.chomik.controller

import com.chomik.domain.User


import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.persistence.Persistence

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserController {

    private val entityManager = Persistence.createEntityManagerFactory("my-jaxrs-app").createEntityManager()

    @GET
    fun getUsers(): List<User> {
        return entityManager.createQuery("SELECT u FROM User u", User::class.java).resultList
    }

    @GET
    @Path("/{id}")
    fun getUser(@PathParam("id") id: Long): Response {
        val user = entityManager.find(User::class.java, id) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(user).build()
    }

    @POST
    fun createUser(user: User): Response {
        entityManager.transaction.begin()
        entityManager.persist(user)
        entityManager.transaction.commit()
        return Response.status(Response.Status.CREATED).entity(user).build()
    }

}
