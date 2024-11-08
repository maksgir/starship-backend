package com.chomik.domain

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    val id: String? = null,

    @Column
    var name: String,

    @Column
    var email: String
)