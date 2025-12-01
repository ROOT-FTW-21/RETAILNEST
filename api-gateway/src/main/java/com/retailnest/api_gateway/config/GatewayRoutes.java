package com.retailnest.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.retailnest.api_gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayRoutes {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public GatewayRoutes(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth_route",
				r -> r.path("/auth/**")
						.filters(f -> f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config())))
						.uri("lb://AUTH-SERVICE"))
				.route("product_route",
						r -> r.path("/product/**").filters(
								f -> f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config())))
								.uri("lb://PRODUCT-SERVICE"))
				.build();
	}
}
