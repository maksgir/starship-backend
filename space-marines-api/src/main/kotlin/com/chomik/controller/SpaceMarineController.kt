package com.chomik.controller

import com.chomik.domain.SpaceMarine
import com.chomik.service.SpaceMarinesService
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/v1/spacemarines")
class SpaceMarineController {

    @Inject
    private lateinit var spaceMarinesService: SpaceMarinesService

    @GET
    @Path("/{space-marine-id}")
    @Produces(MediaType.APPLICATION_XML)
    fun getSpaceMarine(
        @PathParam("space-marine-id") spaceMarineId: Long,
    ): Response {
        val spaceMarine: SpaceMarine?

        try {
            spaceMarine = spaceMarinesService.getSpaceMarineById(spaceMarineId)
        } catch (e: Exception) {
            throw e
        }

        return if (spaceMarine != null) {
            Response.ok().entity(spaceMarine).build()
        } else {
            Response
                .status(Response.Status.NOT_FOUND)
                .build()
        }
    }
}
