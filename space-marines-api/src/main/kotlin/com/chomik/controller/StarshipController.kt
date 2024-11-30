package com.chomik.controller

import com.chomik.domain.dto.CreateStarshipRequest
import com.chomik.domain.dto.SpaceMarineRequestDto
import com.chomik.service.StarshipService
import com.chomik.util.buildBadRequestResponse
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/v1/starship")
class StarshipController {

    @Inject
    private lateinit var starshipService: StarshipService

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    fun createStarship(createStarshipRequest: CreateStarshipRequest): Response {
        return try {
            val createdStarship = starshipService.createStarship(createStarshipRequest.name)
            Response.status(Response.Status.CREATED)
                .entity(createdStarship)
                .build()
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid request: ${e.message}")
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An error occurred while creating the starship.")
                .build()
        }
    }

//    @POST
//    @Path("")
//    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.APPLICATION_XML)
//    fun createSpaceMarine(spaceMarineRequestDto: SpaceMarineRequestDto): Response {
//        try {
//            val spaceMarine = spaceMarinesService.create(spaceMarineRequestDto)
//            return Response.ok().entity(spaceMarine).build()
//        } catch (e: Exception) {
//            return e.buildBadRequestResponse()
//        }
//    }
}