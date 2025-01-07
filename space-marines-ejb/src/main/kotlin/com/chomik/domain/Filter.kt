package com.chomik.domain

import com.chomik.domain.enums.ComparisonSymbol

data class Filter(
    val columnName: String,
    val comparisonSymbol: ComparisonSymbol,
    val value: String
) {
    companion object {
        @JvmStatic
        fun fromString(filter: String): Filter {
            ComparisonSymbol.entries.forEach {
                val splitFilter = filter.split(it.symbol)

                if (splitFilter.size == 2) {
                    return Filter(splitFilter[0], it, splitFilter[1])
                }
            }
            throw IllegalArgumentException("Impossible to build filter for input string: $filter")
        }
    }
}
