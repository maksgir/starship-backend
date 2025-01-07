package com.chomik.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class Chapter(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank
    val name: String,

    val parentLegion: String? = null,

    @field:Positive
    @field:Max(1000)
    @Column(name = "marines_count")
    val marinesCount: Int,

    val world: String? = null
)
