package com.chomik.controller

import com.chomik.domain.SpaceMarine
import com.chomik.service.SpaceMarinesService
import jakarta.inject.Inject
import jakarta.ws.rs.DELETE
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    fun getSpaceMarine(
        @PathParam("id") spaceMarineId: Long,
    ): Response {
        return try {
            val spaceMarine = spaceMarinesService.findById(spaceMarineId)
            if (spaceMarine != null) {
                Response.ok(spaceMarine).build()
            } else {
                Response.status(Response.Status.NOT_FOUND).build()
            }
        } catch (e: Exception) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    fun deleteSpaceMarine(
        @PathParam("id") spaceMarineId: Long,
    ): Response {
        return try {
            spaceMarinesService.deleteById(spaceMarineId)
            return Response.ok().build()
        } catch (e: Exception) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
    }
}
