package com.retailnest.auth_service.service.utility;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.service.applicationService.impl.UserDetailsApplicationServiceImpl;
import com.retailnest.auth_service.service.componentService.IAuthComponentService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	@Autowired
	private IAuthComponentService authComponentService;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generateToken(String userName) {
		UserEntity userEntity;

		if (UserDetailsApplicationServiceImpl.isIdentifierNumber(userName)) {
			userEntity = authComponentService.getUserByMobile(Long.parseLong(userName));
		} else {
			userEntity = authComponentService.getUserByEmail(userName);
		}
		
		Map<String,Object> claims = new HashMap<>();
	    claims.put("userid", userEntity.getUserid());   
	    claims.put("roles", userEntity.getUserRoles().stream().map(e -> e.getRole()).toList());
		
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {

		/*
		 * for 6 months: 6 months ≈ 6 * 30.44 days = 182.64 days 1 day = 24 hours = 24 *
		 * 60 minutes = 24 * 60 * 60 seconds = 24 * 60 * 60 * 1000 milliseconds Now, we
		 * can calculate: 6 months in milliseconds ≈ 182.64 days * 24 * 60 * 60 * 1000
		 * milliseconds
		 */
		return Jwts.builder().claims(claims).subject(userName).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (long) (182.64 * 24 * 60 * 60 * 1000)))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
