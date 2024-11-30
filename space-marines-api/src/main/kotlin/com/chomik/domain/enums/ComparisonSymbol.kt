package com.chomik.domain.enums

enum class ComparisonSymbol(val symbol: String) {
    LESS("<"),
    EQUAL("="),
    GREATER(">");

    companion object {
        @JvmStatic
        fun fromSymbol(symbol: String) = entries.firstOrNull { it.symbol == symbol }
    }
}
