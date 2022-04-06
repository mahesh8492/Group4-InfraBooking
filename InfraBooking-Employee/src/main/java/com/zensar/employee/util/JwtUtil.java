package com.zensar.employee.util;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
	private long expirationTime=60000*10;//60,000miliseconds= 1 minute 
	//secret key is very sensitive should be complex and should not be shared with anyone
	private final String SECRET_KEY="zensar@12345";
	
	public String generateToken(String username) {
		//TODO generate token
		return JWT.create()
		   .withClaim("username", username) //withclaim adds payload
		   .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
		   .sign(Algorithm.HMAC512(SECRET_KEY));
	}
	
	public String validateToken(String token) {
		//ToDo we need to validate token,
		return JWT.require(Algorithm.HMAC512(SECRET_KEY))
		.build()
		.verify(token)
		.getPayload();
		
		
	}
	

}
