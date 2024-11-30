package com.chomik.service

import com.chomik.domain.QueryParams
import com.chomik.domain.SpaceMarine
import com.chomik.domain.dto.SpaceMarineRequestDto
import com.chomik.domain.enums.Category
import com.chomik.repository.SpaceMarinesRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SpaceMarinesService {

    @Inject
    private lateinit var spaceMarinesRepository: SpaceMarinesRepository

    fun create(spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine {
        val spaceMarine = spaceMarineRequestDto.toSpaceMarineEntity()
        spaceMarinesRepository.save(spaceMarine)
        return spaceMarine
    }

    fun findById(spaceMarineId: Long): SpaceMarine? = spaceMarinesRepository.findById(spaceMarineId)

    fun updateById(spaceMarineId: Long, spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine {
        val spaceMarine = spaceMarineRequestDto.toSpaceMarineEntity(id = spaceMarineId)
        spaceMarinesRepository.update(spaceMarine)
        return spaceMarine
    }

    fun deleteById(spaceMarineId: Long) {
        spaceMarinesRepository.deleteById(spaceMarineId)
    }

    fun getSpaceMarines(queryParams: QueryParams): List<SpaceMarine> = spaceMarinesRepository.getSpaceMarines(queryParams)

    fun findGroupedByCreationDate(page: Int, size: Int): Map<String, Int> {
        if (page <= 0 || size <= 0) {
            throw IllegalArgumentException("Page and size must be greater than 0")
        }

        return spaceMarinesRepository.groupByCreationDate(page, size)
    }

    fun countByCategory(category: String): Int {
        if (category !in Category.values().map { e -> e.name }) {
            throw IllegalArgumentException("The provided category '$category' does not exist.")
        }

        return spaceMarinesRepository.countByCategory(category)
    }


}
