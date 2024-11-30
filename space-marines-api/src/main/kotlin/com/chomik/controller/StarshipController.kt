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

    @POST
    @Path("/{starshipId}/load/{spaceMarineId}")
    @Produces(MediaType.APPLICATION_XML)
    fun createSpaceMarine(
        @PathParam("starshipId") starshipId: Long,
        @PathParam("spaceMarineId") spaceMarineId: Long,
    ): Response {
        return try {
            val createdStarshipMarine = starshipService.loadStarshipOnMarine(starshipId, spaceMarineId)
            Response.status(Response.Status.CREATED)
                .entity(createdStarshipMarine)
                .build()
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid request: ${e.message}")
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An error occurred while associating the starship with the marine.")
                .build()
        }
    }
}