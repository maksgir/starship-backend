package com.chomik.service

import com.chomik.domain.SpaceMarine
import com.chomik.repository.SpaceMarinesRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SpaceMarinesService {

    @Inject
    private lateinit var spaceMarinesRepository: SpaceMarinesRepository

    fun getSpaceMarineById(spaceMarineId: Long): SpaceMarine? = spaceMarinesRepository.getSpaceMarine(spaceMarineId)
}
