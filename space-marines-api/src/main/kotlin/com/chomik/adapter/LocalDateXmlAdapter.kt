package com.chomik.adapter

import jakarta.xml.bind.annotation.adapters.XmlAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateXmlAdapter: XmlAdapter<String, LocalDateTime>() {
    private val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun marshal(dateTime: LocalDateTime): String {
        return dateTime.format(dateFormat)
    }

    override fun unmarshal(dateTime: String?): LocalDateTime {
        return LocalDateTime.parse(dateTime, dateFormat)
    }
}
