package com.chomik.domain.dto

import com.chomik.domain.SpaceMarine
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class SpaceMarinesResponseDto(
    val data: List<SpaceMarine>,
    val total: Int,
    val page: Int? = null,
    val limit: Int? = null,
)
