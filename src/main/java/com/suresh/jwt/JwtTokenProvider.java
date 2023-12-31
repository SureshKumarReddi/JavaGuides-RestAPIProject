package com.suresh.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.suresh.exception.BlogAPIException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	// generate JWT token
	public String generateToken(Authentication authentication) {

		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String userName = authentication.getName();

		String token = Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(key()).compact();

		return token;
	}

	private Key key() {

		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	// get username from the token

	public String getUserName(String token) {

		String userName = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody()
				.getSubject();
		return userName;
	}

	// validate the jwt token
	public boolean validateToken(String token) {

		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(token);

			return true;

		} catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "invalid jwt token");
		}
	}
}
