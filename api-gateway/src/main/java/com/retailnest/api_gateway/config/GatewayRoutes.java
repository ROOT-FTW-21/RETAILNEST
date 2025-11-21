package com.retailnest.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {
	@Bean 
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth_route", r -> r.path("/auth/**")
                .uri("lb://AUTH-SERVICE"))
            .route("profile_route", r -> r.path("/pro/**")
                .uri("lb://PROFILE-SERVICE"))
            .build();
    }
}
