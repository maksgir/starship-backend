package com.starship.unloadmanager.config;

import com.starship.unloadmanager.client.SpaceMarineClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SpaceMarineClient spaceMarineClient(@Value("${space.marine.url}") String spaceMarineUrl, RestTemplate restTemplate) {
        return new SpaceMarineClient(spaceMarineUrl, restTemplate);
    }
}
