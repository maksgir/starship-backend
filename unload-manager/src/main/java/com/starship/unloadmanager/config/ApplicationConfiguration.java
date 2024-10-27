package com.starship.unloadmanager.config;

import com.starship.unloadmanager.client.SpaceMarineClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public SpaceMarineClient spaceMarineClient(@Value("${space.marine.url}") String spaceMarineUrl) {
        return new SpaceMarineClient(spaceMarineUrl, new RestTemplate());
    }
}
