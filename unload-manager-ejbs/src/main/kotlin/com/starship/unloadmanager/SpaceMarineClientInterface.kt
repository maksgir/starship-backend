package com.starship.unloadmanager

import jakarta.ejb.Remote

@Remote
interface SpaceMarineClientInterface {
    fun removeSpaceMarineFromStarship(starshipId: Long, spaceMarineId: Long): SpaceMarineResponse

    fun removeAllSpaceMarinesFromStarship(starshipId: Long): SpaceMarineResponse
}
