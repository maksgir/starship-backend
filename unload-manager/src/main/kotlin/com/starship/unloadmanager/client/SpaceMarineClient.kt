package com.starship.unloadmanager.client

import com.starship.unloadmanager.dto.BadParamsResponseDto
import com.starship.unloadmanager.exception.BadRequestException
import com.starship.unloadmanager.exception.ResourceException
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

class SpaceMarineClient(private val spaceMarineUrl: String, private val restTemplate: RestTemplate) {
    fun removeSpaceMarineFromStarship(starshipId: Long?, spaceMarineId: Long?): String {
        val url = "$spaceMarineUrl/starship/$starshipId/unload/$spaceMarineId"
        val responseBytes = restTemplate.execute(url, HttpMethod.POST, null, { response ->
            if (response.statusCode.isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw BadRequestException(
                    BadParamsResponseDto("Starship $starshipId or space marine $spaceMarineId was not found")
                )
            }
            if (response.statusCode.isError) {
                throw ResourceException(
                    response.statusCode as HttpStatus,
                    "Got error while removing space marine: ${response.body}"
                )
            }
            response.body.readAllBytes()
        })

        return String(responseBytes)
    }

    fun removeAllSpaceMarinesFromStarship(starshipId: Long?): String {
        val url = "$spaceMarineUrl/starship/$starshipId/unload-all"
        val responseBytes = restTemplate.execute(url, HttpMethod.POST, null, { response ->
            if (response.statusCode.isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw BadRequestException(BadParamsResponseDto("Starship $starshipId was not found"))
            }
            if (response.statusCode.isError) {
                throw ResourceException(
                    response.statusCode as HttpStatus,
                    "Got error while removing all space marines: ${response.body}"
                )
            }
            response.body.readAllBytes()
        })

        return String(responseBytes)
    }
}
