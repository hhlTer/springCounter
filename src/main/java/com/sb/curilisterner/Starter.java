package com.sb.curilisterner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {"com.sb.curilisterner.domain.resources"})

public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
