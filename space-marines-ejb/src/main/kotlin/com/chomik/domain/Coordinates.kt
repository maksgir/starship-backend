package com.chomik.domain

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement
import java.io.Serializable

@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class Coordinates(

    @field:NotNull
    @field:Max(315)
    val x: Double,

    @field:NotNull
    @field:Max(708)
    val y: Int
): Serializable
