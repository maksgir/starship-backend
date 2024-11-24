package com.chomik.domain

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull

@Embeddable
data class Coordinates(

    @field:NotNull
    @field:Max(315)
    val x: Double,

    @field:NotNull
    @field:Max(708)
    val y: Int
)