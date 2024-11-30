package com.chomik.domain.dto

import com.chomik.domain.Chapter
import com.chomik.domain.Coordinates
import com.chomik.domain.SpaceMarine
import com.chomik.domain.enums.Category
import com.chomik.domain.enums.MeleeWeapon
import com.chomik.domain.enums.WeaponType
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class SpaceMarineRequestDto(
    val name: String,
    val coordinates: Coordinates,
    val health: Double,
    val category: Category? = null,
    val weaponType: WeaponType? = null,
    val meleeWeapon: MeleeWeapon,
    val chapterRequestDto: ChapterRequestDto
) {
    fun toSpaceMarineEntity(id: Long? = null): SpaceMarine = SpaceMarine(
        id = id,
        name = name,
        coordinates = Coordinates(coordinates.x, coordinates.y),
        health = health,
        category = category,
        weaponType = weaponType,
        meleeWeapon = meleeWeapon,
        chapter = Chapter(
            name = chapterRequestDto.name,
            parentLegion = chapterRequestDto.parentLegion,
            marinesCount = chapterRequestDto.marinesCount,
            world = chapterRequestDto.world
        )
    )
}
