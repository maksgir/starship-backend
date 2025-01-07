package com.chomik.service.impl

import com.chomik.domain.QueryParams
import com.chomik.domain.SpaceMarine
import com.chomik.domain.dto.SpaceMarineRequestDto
import com.chomik.domain.enums.Category
import com.chomik.repository.SpaceMarinesRepository
import com.chomik.service.SpaceMarinesService
import jakarta.ejb.Stateless
import jakarta.inject.Inject
import org.jboss.ejb3.annotation.Pool

@Stateless
@Pool("space-marines-pool")
class SpaceMarinesServiceImpl : SpaceMarinesService {

    @Inject
    private lateinit var spaceMarinesRepository: SpaceMarinesRepository

    override fun create(spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine {
        val spaceMarine = spaceMarineRequestDto.toSpaceMarineEntity()
        spaceMarinesRepository.save(spaceMarine)
        return spaceMarine
    }

    override fun findById(spaceMarineId: Long): SpaceMarine? = spaceMarinesRepository.findById(spaceMarineId)

    override fun updateById(spaceMarineId: Long, spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine {
        val spaceMarine = spaceMarineRequestDto.toSpaceMarineEntity(id = spaceMarineId)
        spaceMarinesRepository.update(spaceMarine)
        return spaceMarine
    }

    override fun deleteById(spaceMarineId: Long) {
        spaceMarinesRepository.deleteById(spaceMarineId)
    }

    override fun getSpaceMarines(queryParams: QueryParams): List<SpaceMarine> =
        spaceMarinesRepository.getSpaceMarines(queryParams)

    override fun findGroupedByCreationDate(page: Int, size: Int): Map<String, Int> {
        if (page <= 0 || size <= 0) {
            throw IllegalArgumentException("Page and size must be greater than 0")
        }

        return spaceMarinesRepository.groupByCreationDate(page, size)
    }

    override fun countByCategory(category: String): Int {
        if (category !in Category.entries.map { e -> e.name }) {
            throw IllegalArgumentException("The provided category '$category' does not exist.")
        }

        return spaceMarinesRepository.countByCategory(category)
    }

    override fun searchByName(nameSubstring: String): List<SpaceMarine> {
        if (nameSubstring.isBlank()) {
            throw IllegalArgumentException("The name substring must not be empty.")
        }

        return spaceMarinesRepository.searchByName(nameSubstring)
    }
}
