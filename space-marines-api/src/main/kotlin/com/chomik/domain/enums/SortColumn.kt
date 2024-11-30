package com.chomik.domain.enums

import com.chomik.util.camelToSnake

enum class SortColumn(val columnName: String) {
    ID("id"),
    NAME("name"),
    CREATION_DATE("creation_date"),
    HEALTH("health"),
    CATEGORY("category"),
    WEAPON_TYPE("weapon_type"),
    MELEE_WEAPON("melee_weapon");

    companion object {
        @JvmStatic
        fun fromColumnName(columnName: String) = entries.first { it.columnName == columnName.lowercase().camelToSnake() }
    }
}
