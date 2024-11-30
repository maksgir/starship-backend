package com.chomik.service

import com.chomik.domain.Starship
import com.chomik.domain.StarshipMarine
import com.chomik.repository.StarshipRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class StarshipService {

    @Inject
    private lateinit var starshipRepository: StarshipRepository

    fun createStarship(name: String): Starship {
        if (name.isBlank()) {
            throw IllegalArgumentException("Starship name cannot be blank")
        }
        val starship = Starship(name = name)
        return starshipRepository.createStarship(starship)
    }

    fun loadStarshipOnMarine(starshipId: Long, spaceMarineId: Long): StarshipMarine {
        if (starshipId <= 0 || spaceMarineId <= 0) {
            throw IllegalArgumentException("Starship ID and Space Marine ID must be positive numbers")
        }

        val starshipMarine = StarshipMarine(
            starshipId = starshipId,
            spaceMarineId = spaceMarineId
        )

        return starshipRepository.createStarshipMarine(starshipMarine)
    }

    fun unloadMarine(starshipId: Long, spaceMarineId: Long): Int {
        val rowsAffected = starshipRepository.deleteMarineFromStarship(starshipId, spaceMarineId)
        return rowsAffected
    }

    fun unloadAllMarines(starshipId: Long): Int {
        val rowsAffected = starshipRepository.deleteAllMarinesFromStarship(starshipId)
        return rowsAffected
    }
}
