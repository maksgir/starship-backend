package com.chomik.service

import com.chomik.domain.Starship
import com.chomik.domain.StarshipMarine
import jakarta.ejb.Remote

@Remote
interface StarshipService {
    fun createStarship(name: String): Starship
    fun loadStarshipOnMarine(starshipId: Long, spaceMarineId: Long): StarshipMarine
    fun unloadMarine(starshipId: Long, spaceMarineId: Long): Int
    fun unloadAllMarines(starshipId: Long): Int
}
