package com.starship.unloadmanager.tmp;

import com.starship.unloadmanager.SpaceMarineResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface StarshipUnloadWebService {

    @WebMethod
    SpaceMarineResponse unloadSpaceMarine(
            @WebParam(name = "starshipId") long starshipId,
            @WebParam(name = "spaceMarineId") long spaceMarineId
    );

    @WebMethod
    SpaceMarineResponse unloadAllSpaceMarines(
            @WebParam(name = "starshipId") long starshipId
    );
}
