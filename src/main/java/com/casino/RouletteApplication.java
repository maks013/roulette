package com.casino;

import com.casino.infrastructure.security.jwt.properties.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(JwtConfigurationProperties.class)
public class RouletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouletteApplication.class, args);
    }

}
