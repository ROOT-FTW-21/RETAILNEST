package com.retailnest.api_gateway.util;

import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public Claims extractAllClaims(String token) throws JwtException {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build()
				.parseClaimsJws(token).getBody();
	}

	public String extractUserIdentifier(String token) {
		return extractAllClaims(token).getSubject(); // Could be email or mobile
	}

	public List<String> extractRoles(String token) {
		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) extractAllClaims(token).get("roles");
		return roles == null ? List.of() : roles;
	}

	public String extractLoginType(String token) {
		return extractAllClaims(token).get("login_type", String.class);
	}

	public void validateToken(String token) {
		extractAllClaims(token); // will throw if invalid
	}
}
