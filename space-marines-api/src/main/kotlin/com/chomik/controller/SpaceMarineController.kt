package com.chomik.controller

import com.chomik.domain.Filter
import com.chomik.domain.QueryParams
import com.chomik.domain.dto.ErrorResponseDto
import com.chomik.domain.dto.SpaceMarineRequestDto
import com.chomik.domain.dto.SpaceMarinesResponseDto
import com.chomik.domain.enums.SortColumn
import com.chomik.domain.enums.SortOrder
import com.chomik.service.SpaceMarinesService
import com.chomik.util.buildBadRequestResponse
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("/v1/spacemarines")
class SpaceMarineController {

    @Inject
    private lateinit var spaceMarinesService: SpaceMarinesService

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_XML)
    fun getSpaceMarines(
        @QueryParam("sortBy") sortBy: String?,
        @QueryParam("orderBy") sortOrder: String?,
        @QueryParam("filters") filters: List<String>?,
        @QueryParam("page") @DefaultValue(DEFAULT_PAGE_NUMBER) page: Int,
        @QueryParam("size") @DefaultValue(DEFAULT_PAGE_SIZE) size: Int,
    ): Response {
        val queryParams = QueryParams(
            sortBy = try {
                sortBy?.split(SORT_SPLIT_SYMBOL)?.map { SortColumn.fromColumnName(it) }
            } catch (e: NoSuchElementException) {
                return e.buildBadRequestResponse()
            },
            sortOrder = try {
                sortOrder?.let { SortOrder.valueOf(it.uppercase()) }
            } catch (e: IllegalArgumentException) {
                return e.buildBadRequestResponse()
            },
            filters = try {
                filters?.map { Filter.fromString(it) }
            } catch (e: IllegalArgumentException) {
                return e.buildBadRequestResponse()
            },
            page = page,
            size = size,
        )

        try {
            val spaceMarine = spaceMarinesService.getSpaceMarines(queryParams)
            return Response.ok().entity(
                SpaceMarinesResponseDto(
                    data = spaceMarine,
                    total = spaceMarine.size,
                    limit = queryParams.size,
                    page = queryParams.page
                )
            ).build()
        } catch (e: Exception) {
            throw e
        }
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun createSpaceMarine(spaceMarineRequestDto: SpaceMarineRequestDto): Response {
        try {
            val spaceMarine = spaceMarinesService.create(spaceMarineRequestDto)
            return Response.ok().entity(spaceMarine).build()
        } catch (e: Exception) {
            return e.buildBadRequestResponse()
        }
    }

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
                Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponseDto(Response.Status.NOT_FOUND.statusCode, "User with id $spaceMarineId doesn't exist"))
                    .build()
            }
        } catch (e: Exception) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    fun updateSpaceMarine(@PathParam("id") spaceMarineId: Long, spaceMarineRequestDto: SpaceMarineRequestDto): Response {
        try {
            val spaceMarine = spaceMarinesService.updateById(spaceMarineId, spaceMarineRequestDto)
            return Response.ok().entity(spaceMarine).build()
        } catch (e: Exception) {
            return e.buildBadRequestResponse()
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

    companion object {
        const val DEFAULT_PAGE_NUMBER = "1"
        const val DEFAULT_PAGE_SIZE = "20"
        const val SORT_SPLIT_SYMBOL = ","
    }


    @GET
    @Path("/group-by-creation-date")
    @Produces(MediaType.APPLICATION_XML)
    fun getSpaceMarinesGroupedByCreationDate(
        @QueryParam("page") page: Int?,
        @QueryParam("size") size: Int?
    ): Response {
        val pageNumber = page ?: 1
        val pageSize = size ?: 20

        try {
            if (pageNumber <= 0 || pageSize <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Неверные параметры пагинации").build()
            }

            val result = spaceMarinesService.findGroupedByCreationDate(pageNumber, pageSize)

            return Response.ok(result).build()
        } catch (e: Exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Ошибка обработки запроса").build()
        }
    }

    @GET
    @Path("/count-by-category")
    @Produces(MediaType.APPLICATION_XML)
    fun countByCategory(@QueryParam("category") category: String): Response {
        return try {
            val count = spaceMarinesService.countByCategory(category)
            Response.ok(count).build()
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponseDto(Response.Status.BAD_REQUEST.statusCode, "Invalid category: ${e.message}"))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An error occurred while processing the request.")
                .build()
        }
    }

    @GET
    @Path("/search-by-name")
    @Produces(MediaType.APPLICATION_XML)
    fun searchByName(@QueryParam("nameSubstring") nameSubstring: String): Response {
        return try {
            val results = spaceMarinesService.searchByName(nameSubstring)
            if (results.isEmpty()) {
                Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponseDto(Response.Status.NOT_FOUND.statusCode, "No Space Marines found matching the given name substring."))
                    .build()
            } else {
                Response.ok(SpaceMarinesResponseDto(data = results, total = results.size)).build()
            }
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid request: ${e.message}")
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An error occurred while processing the request.")
                .build()
        }
    }
}
