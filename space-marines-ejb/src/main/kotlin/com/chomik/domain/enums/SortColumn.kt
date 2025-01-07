package com.chomik.domain.enums

import com.chomik.util.snakeToCamel

enum class SortColumn(val columnName: String) {
    ID("id"),
    NAME("name"),
    CREATION_DATE("creationDate"),
    HEALTH("health"),
    CATEGORY("category"),
    WEAPON_TYPE("weaponType"),
    MELEE_WEAPON("meleeWeapon");

    companion object {
        @JvmStatic
        fun fromColumnName(columnName: String) = entries.first { it.columnName == columnName.lowercase().snakeToCamel() }
    }
}
