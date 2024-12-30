package com.starship.unloadmanager.config

import com.starship.unloadmanager.client.SpaceMarineClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ApplicationConfiguration {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun spaceMarineClient(
        @Value("\${space.marine.url}") spaceMarineUrl: String,
        restTemplate: RestTemplate
    ): SpaceMarineClient {
        return SpaceMarineClient(spaceMarineUrl, restTemplate)
    }
}
