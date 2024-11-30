package com.chomik.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["starshipId", "spaceMarineId"])]
)
data class StarshipMarine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotNull
    @Column(nullable = false)
    val starshipId: Long,

    @field:NotNull
    @Column(nullable = false)
    val spaceMarineId: Long,

    @ManyToOne(optional = false)
    @JoinColumn(name = "starshipId", referencedColumnName = "id", insertable = false, updatable = false)
    val starship: Starship? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "spaceMarineId", referencedColumnName = "id", insertable = false, updatable = false)
    val spaceMarine: SpaceMarine? = null
)
