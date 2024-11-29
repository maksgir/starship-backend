package com.chomik.domain

import com.chomik.domain.enums.Category
import com.chomik.domain.enums.MeleeWeapon
import com.chomik.domain.enums.WeaponType
import java.time.LocalDateTime

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement

@Entity
@XmlRootElement(name = "space-marine")
@XmlAccessorType(XmlAccessType.FIELD)
data class SpaceMarine(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank
    val name: String? = null,

    @Embedded
    val coordinates: Coordinates? = null,

    @Column(name = "creation_date", updatable = false)
    val creationDate: LocalDateTime = LocalDateTime.now(),

    @field:Positive
    val health: Double? = null,

    @Enumerated(EnumType.STRING)
    val category: Category? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_type")
    val weaponType: WeaponType? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "melee_weapon", nullable = false)
    val meleeWeapon: MeleeWeapon? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "chapter_id", nullable = false)
    val chapter: Chapter? = null
)
