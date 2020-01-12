package com.restaurant.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.restaurant.api.domain.constant.Constant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String BEARER = "Bearer ";
	private static final String EMPTY = "";
	private static final String TOKEN_SECRET_KEY = "MKavZ56BFtfWXoRx1RJ0dbQ5NQ1c1mZxOXFkKEtr-Cnxb6YZ-opkZHr_jiv7G4H9bBZYldiAYC2fs-Syuo-kmQ";	
	private static final int TOKEN_EXPIRATION_MILLISECS = 300000;
	private static final String TOKEN_SECRET_KEY_SUFFIX_DATE_MASK = "ddMMyyyy";
	
	public static String getToken(String username) {
		
		String today = getToday();		
		String token = Jwts.builder().setId(Constant.JWT_PROJECT_ID).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(
								new Date(System.currentTimeMillis()
										+ TOKEN_EXPIRATION_MILLISECS)).signWith(SignatureAlgorithm.HS512,
												(TOKEN_SECRET_KEY + today).getBytes()).compact();

		return Constant.BEARER + token;
	}
	
	
	public static Claims validateToken(HttpServletRequest request) {
		Claims claims = null;
		String today = getToday();
		if (existsJWTToken(request)) {			
			String jwtToken = request.getHeader(AUTHORIZATION_HEADER_KEY).replace(BEARER, EMPTY);			
			claims = Jwts.parser().setSigningKey((TOKEN_SECRET_KEY + today).getBytes()).parseClaimsJws(jwtToken).getBody();
		}
		
		return claims;
		
	}
	
	private static boolean existsJWTToken(HttpServletRequest request) {
		boolean existsToken = true;
		String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER_KEY);
		
		if (authenticationHeader == null || !authenticationHeader.startsWith(BEARER)) {
			existsToken = false;
		}
		
		return existsToken;
	}
	
	private static String getToday() {
		String today = new SimpleDateFormat(TOKEN_SECRET_KEY_SUFFIX_DATE_MASK).format(new Date());
		return today;
	}

}
