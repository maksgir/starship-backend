package com.chomik.domain.dto

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class ChapterRequestDto(
    val name: String,
    val parentLegion: String? = null,
    val marinesCount: Int,
    val world: String? = null
)
