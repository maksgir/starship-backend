package com.chomik.util

import jakarta.ejb.Stateless

@Stateless
class PropertiesUtil {
    private val propertiesMap: MutableMap<String, String> = mutableMapOf()

    init {
        val propertiesStream = this.javaClass.getResourceAsStream("/application.properties")

        propertiesStream?.readAllBytes()?.toString(Charsets.UTF_8)
            ?.split("\n")
            ?.forEach { fullProperty ->
                val splittedProperty = fullProperty.split("=")

                if (splittedProperty.size == 2) {
                    propertiesMap[splittedProperty[0]] = splittedProperty[1]
                }
            }
    }

    fun getValueByPropertyNameOrEmpty(name: String): String {
        return propertiesMap[name] ?: ""
    }
}
