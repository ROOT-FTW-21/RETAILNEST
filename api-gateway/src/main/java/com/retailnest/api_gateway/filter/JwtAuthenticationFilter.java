package com.retailnest.api_gateway.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import com.retailnest.api_gateway.util.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

	private static final List<String> openEndpoints = List.of("/auth/create-user", "/auth/login");

	@Autowired
	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter() {
		super(Config.class);
	}

	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String path = exchange.getRequest().getPath().value();

			// Skip JWT validation for public endpoints
			if (openEndpoints.contains(path)) {
				return chain.filter(exchange);
			}

			String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}

			String token = authHeader.substring(7);

			try {
				Claims claims = jwtUtil.extractAllClaims(token);

				// Forward key claims as headers
				ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
						.header("X-UserId", claims.get("userid", String.class))
						//.header("X-UserName", claims.get("name", String.class))
						//.header("X-LoginType", claims.get("login_type", String.class))
						.header("X-Roles", String.join(",", (List<String>) claims.get("roles"))).build();

				ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
				return chain.filter(mutatedExchange);

			} catch (Exception e) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
		};
	}

	public static class Config {
		// Reserved for future config extensions
	}
}
