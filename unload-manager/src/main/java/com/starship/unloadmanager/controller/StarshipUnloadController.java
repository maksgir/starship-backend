package com.starship.unloadmanager.controller;

import com.starship.unloadmanager.client.SpaceMarineClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/starship")
public class StarshipUnloadController {

    private final SpaceMarineClient spaceMarineClient;

    public StarshipUnloadController(SpaceMarineClient spaceMarineClient) {
        this.spaceMarineClient = spaceMarineClient;
    }

    @PostMapping("/{starshipId}/unload/{spaceMarineId}")
    public String unloadSpaceMarine(
        @PathVariable(name = "starshipId") Long starshipId,
        @PathVariable(name = "spaceMarineId") Long spaceMarineId
    ) {
        return spaceMarineClient.removeSpaceMarineFromStarship(starshipId, spaceMarineId);
    }

    @PostMapping("/{starshipId}/unload-all")
    public String unloadAllSpaceMarines(@PathVariable(name = "starshipId") Long starshipId) {
        return spaceMarineClient.removeAllSpaceMarinesFromStarship(starshipId);
    }
}
