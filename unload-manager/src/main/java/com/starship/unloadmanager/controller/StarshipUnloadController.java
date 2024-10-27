package com.starship.unloadmanager.controller;

import com.starship.unloadmanager.client.SpaceMarineClient;
import com.starship.unloadmanager.dto.BadParamsResponseDto;
import com.starship.unloadmanager.exception.BadRequestException;
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
    public void unloadSpaceMarine(
        @PathVariable(name = "starshipId") Long starshipId,
        @PathVariable(name = "spaceMarineId") Long spaceMarineId
    ) {
        var existenceResponse = spaceMarineClient.checkSpaceMarineExistInStarship(starshipId, spaceMarineId);

        if (Boolean.FALSE.equals(existenceResponse.getBody())) {
            throw new BadRequestException(
                new BadParamsResponseDto("Space marine %d doesn't exists on starship %d".formatted(spaceMarineId, starshipId))
            );
        }

        spaceMarineClient.removeSpaceMarineFromStarship(starshipId, spaceMarineId);
    }

    @PostMapping("/{starshipId}/unload-all")
    public void unloadAllSpaceMarines(@PathVariable(name = "starshipId") Long starshipId) {
        spaceMarineClient.removeAllSpaceMarinesFromStarship(starshipId);
    }
}
