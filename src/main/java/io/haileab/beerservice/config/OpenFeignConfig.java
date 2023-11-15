package io.haileab.beerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${htg.brewery.inventory-username}") String userName,
            @Value("${htg.brewery.inventory-password}") String password
    ){
        return new BasicAuthRequestInterceptor(userName, password);
    }
}
