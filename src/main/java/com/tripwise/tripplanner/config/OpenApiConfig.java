package com.tripwise.tripplanner.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi tripPlannerApi() {
        return GroupedOpenApi.builder()
                .group("tripplanner")
                .pathsToMatch("/tripplanner/**")
                .addOpenApiCustomizer(openApi ->
                        openApi.info(new Info()
                                .title("TripPlanner API")
                                .version("1.0.0")
                                .description("Itinerary & Packing Lists service for TripWise")
                        )
                )
                .build();
    }
}
