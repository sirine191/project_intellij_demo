package com.springdemo.ipvproject;

import com.springdemo.ipvproject.configuration.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
class IpvprojectApplicationTests {

    @Test
    void contextLoads() {
    }
}