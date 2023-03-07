package com.spring.bike.bikeshopapp;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class BikeShopAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeShopAppApplication.class, args);
    }

}
