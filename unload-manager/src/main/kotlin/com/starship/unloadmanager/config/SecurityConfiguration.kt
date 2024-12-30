package com.starship.unloadmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { csrfConfigurer -> csrfConfigurer.disable() }
            .authorizeHttpRequests { security ->
                security.anyRequest().permitAll()
            }
            .build()
    }
}
