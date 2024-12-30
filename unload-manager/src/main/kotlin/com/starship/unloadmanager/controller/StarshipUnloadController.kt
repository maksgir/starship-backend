package com.starship.unloadmanager.controller

import com.starship.unloadmanager.client.SpaceMarineClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/starship")
class StarshipUnloadController(private val spaceMarineClient: SpaceMarineClient) {
    @PostMapping("/{starshipId}/unload/{spaceMarineId}")
    fun unloadSpaceMarine(
        @PathVariable(name = "starshipId") starshipId: Long,
        @PathVariable(name = "spaceMarineId") spaceMarineId: Long,
    ): String {
        return spaceMarineClient.removeSpaceMarineFromStarship(starshipId, spaceMarineId)
    }

    @PostMapping("/{starshipId}/unload-all")
    fun unloadAllSpaceMarines(@PathVariable(name = "starshipId") starshipId: Long): String {
        return spaceMarineClient.removeAllSpaceMarinesFromStarship(starshipId)
    }
}
