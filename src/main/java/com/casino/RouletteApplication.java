package com.casino;

import com.casino.infrastructure.security.jwt.properties.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfigurationProperties.class)
public class RouletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouletteApplication.class, args);
    }

}
