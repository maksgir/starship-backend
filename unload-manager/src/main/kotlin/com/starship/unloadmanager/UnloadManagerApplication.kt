package com.starship.unloadmanager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class UnloadManagerApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(UnloadManagerApplication::class.java)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(UnloadManagerApplication::class.java, *args)
        }
    }
}
