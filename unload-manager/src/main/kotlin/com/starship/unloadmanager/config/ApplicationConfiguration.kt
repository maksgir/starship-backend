package com.starship.unloadmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jndi.JndiObjectFactoryBean

@Configuration
class ApplicationConfiguration {
    @Bean
    fun ejbBean(): JndiObjectFactoryBean {
        val factoryBean = JndiObjectFactoryBean()
        factoryBean.jndiName = "ejb:/unload-manager-ejbs-0.0.1-jar-with-dependencies/SpaceMarineClientImpl!com.starship.unloadmanager.SpaceMarineClientInterface"
        factoryBean.isResourceRef = true
        return factoryBean
    }
}
