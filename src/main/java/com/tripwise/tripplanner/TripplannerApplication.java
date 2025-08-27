package com.tripwise.tripplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.tripwise.tripplanner.model")
@EnableJpaRepositories(basePackages = "com.tripwise.tripplanner.repo")
public class TripplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripplannerApplication.class, args);
    }

}
