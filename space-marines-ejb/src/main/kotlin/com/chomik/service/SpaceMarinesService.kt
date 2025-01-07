package com.chomik.service

import com.chomik.domain.QueryParams
import com.chomik.domain.SpaceMarine
import com.chomik.domain.dto.SpaceMarineRequestDto
import jakarta.ejb.Remote

@Remote
interface SpaceMarinesService {
    fun create(spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine
    fun findById(spaceMarineId: Long): SpaceMarine?
    fun updateById(spaceMarineId: Long, spaceMarineRequestDto: SpaceMarineRequestDto): SpaceMarine
    fun deleteById(spaceMarineId: Long)
    fun getSpaceMarines(queryParams: QueryParams): List<SpaceMarine>
    fun findGroupedByCreationDate(page: Int, size: Int): Map<String, Int>
    fun countByCategory(category: String): Int
    fun searchByName(nameSubstring: String): List<SpaceMarine>
}
