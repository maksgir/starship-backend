package com.starship.unloadmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            cors {
                configurationSource = CorsConfigurationSource {
                    val configuration = CorsConfiguration().applyPermitDefaultValues()
                    configuration.allowedOrigins = listOf("http://localhost", "https://se.ifmo.ru")
                    configuration.addAllowedMethod(HttpMethod.POST)
                    configuration
                }
            }
            csrf { disable() }
        }

        return http.build()
    }
}
