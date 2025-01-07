package com.chomik.service.impl

import com.chomik.domain.Starship
import com.chomik.domain.StarshipMarine
import com.chomik.repository.StarshipRepository
import com.chomik.service.StarshipService
import jakarta.ejb.Stateless
import jakarta.inject.Inject
import org.jboss.ejb3.annotation.Pool

@Stateless
@Pool("space-marines-pool")
class StarshipServiceImpl : StarshipService {

    @Inject
    private lateinit var starshipRepository: StarshipRepository

    override fun createStarship(name: String): Starship {
        if (name.isBlank()) {
            throw IllegalArgumentException("Starship name cannot be blank")
        }
        val starship = Starship(name = name)
        return starshipRepository.createStarship(starship)
    }

    override fun loadStarshipOnMarine(starshipId: Long, spaceMarineId: Long): StarshipMarine {
        if (starshipId <= 0 || spaceMarineId <= 0) {
            throw IllegalArgumentException("Starship ID and Space Marine ID must be positive numbers")
        }

        val starshipMarine = StarshipMarine(
            starshipId = starshipId,
            spaceMarineId = spaceMarineId
        )

        return starshipRepository.createStarshipMarine(starshipMarine)
    }

    override fun unloadMarine(starshipId: Long, spaceMarineId: Long): Int {
        val rowsAffected = starshipRepository.deleteMarineFromStarship(starshipId, spaceMarineId)
        return rowsAffected
    }

    override fun unloadAllMarines(starshipId: Long): Int {
        val rowsAffected = starshipRepository.deleteAllMarinesFromStarship(starshipId)
        return rowsAffected
    }
}
