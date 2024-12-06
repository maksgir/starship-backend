package com.starship.unloadmanager.client;

import com.starship.unloadmanager.dto.BadParamsResponseDto;
import com.starship.unloadmanager.exception.BadRequestException;
import com.starship.unloadmanager.exception.ResourceException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

public class SpaceMarineClient {
    private final String spaceMarineUrl;
    private final RestTemplate restTemplate;

    public SpaceMarineClient(String spaceMarineUrl, RestTemplate restTemplate) {
        this.spaceMarineUrl = spaceMarineUrl;
        this.restTemplate = restTemplate;
    }

    public String removeSpaceMarineFromStarship(Long starshipId, Long spaceMarineId) {
        String url = spaceMarineUrl + "/starship/%d/unload/%d".formatted(starshipId, spaceMarineId);
        byte[] responseBytes = restTemplate.execute(url, HttpMethod.POST, null, response -> {
            if (response.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw new BadRequestException(
                    new BadParamsResponseDto("Starship %d or space marine %d was not found".formatted(starshipId, spaceMarineId))
                );
            }

            if (response.getStatusCode().isError()) {
                throw new ResourceException((HttpStatus) response.getStatusCode(), "Got error while removing space marine: %s".formatted(response.getBody()));
            }

            return response.getBody().readAllBytes();
        });

        return new String(responseBytes);
    }

    public String removeAllSpaceMarinesFromStarship(Long starshipId) {
        String url = spaceMarineUrl + "/starship/%d/unload-all".formatted(starshipId);
        byte[] responseBytes = restTemplate.execute(url, HttpMethod.POST, null, response -> {
            if (response.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
                throw new BadRequestException(new BadParamsResponseDto("Starship %d was not found".formatted(starshipId)));
            }

            if (response.getStatusCode().isError()) {
                throw new ResourceException((HttpStatus) response.getStatusCode(), "Got error while removing all space marines: %s".formatted(response.getBody()));
            }

            return response.getBody().readAllBytes();
        });

        return new String(responseBytes);
    }
}
