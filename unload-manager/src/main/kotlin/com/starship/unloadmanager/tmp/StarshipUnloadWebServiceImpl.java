package com.starship.unloadmanager.tmp;

import com.starship.unloadmanager.SpaceMarineClientInterface;
import com.starship.unloadmanager.SpaceMarineResponse;
import jakarta.ejb.EJB;
import jakarta.jws.WebService;

@WebService(serviceName = "StarshipUnloadWebService", endpointInterface = "com.starship.unloadmanager.tmp.StarshipUnloadWebService")
public class StarshipUnloadWebServiceImpl implements StarshipUnloadWebService {

    @EJB
    private SpaceMarineClientInterface spaceMarineClient;


    @Override
    public SpaceMarineResponse unloadSpaceMarine(long starshipId, long spaceMarineId) {
        return spaceMarineClient.removeSpaceMarineFromStarship(starshipId, spaceMarineId);
    }

    @Override
    public SpaceMarineResponse unloadAllSpaceMarines(long starshipId) {
        return spaceMarineClient.removeAllSpaceMarinesFromStarship(starshipId);
    }
}
