package com.chomik.service

import com.chomik.domain.Starship
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
}
