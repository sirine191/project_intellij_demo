package com.springdemo.ipvproject;

import com.springdemo.ipvproject.configuration.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration.class)
public class IpvprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpvprojectApplication.class, args);
    }
}

