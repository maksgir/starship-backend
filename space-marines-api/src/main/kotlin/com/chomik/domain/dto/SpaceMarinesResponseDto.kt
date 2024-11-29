package com.chomik.domain.dto

import com.chomik.domain.SpaceMarine
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "space_marine")
@XmlAccessorType(XmlAccessType.FIELD)
data class SpaceMarinesResponseDto(
    @XmlElement val total: Int,
    @XmlElement val limit: Int?,
    @XmlElement val offset: Int?,
    @XmlElement val data: List<SpaceMarine>,
)
