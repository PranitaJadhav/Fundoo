package com.bridgelabz.demo.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

@Component
public class TokenService {

	// it is a random generated string

	public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";

	// create token based onHMAC256 claim:// allow us to add any data into tokenthis
	// data and this data can be fetched from token after decoding it

	public String createToken(String emailid) throws UnsupportedEncodingException {

		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		String token = JWT.create().withClaim("emailid", emailid).sign(algorithm);
		return token;

		// log TokenSigning Failed

	}

	
	public String getUserToken(String token) {
		System.out.println("in token");
		Claim claim = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaim("emailid");

		System.out.println("claim" + claim);
		return claim.asString();

	}


	
	 

	
	/*
	 * public String decodetoken(String token) { String id;
	 * 
	 * Verification verification = null;
	 * 
	 * try {
	 * 
	 * verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
	 * 
	 * } catch (IllegalArgumentException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * JWTVerifier jwtverifier = verification.build();
	 * 
	 * DecodedJWT decodejwt = jwtverifier.verify(token);
	 * 
	 * Claim claim = decodejwt.getClaim("emailid");
	 * 
	 * id = claim.asString();
	 * 
	 * return id; }
	 */

	/*
	 * public String decodetoken(String token) throws UnsupportedEncodingException {
	 * try { Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET); JWTVerifier
	 * verifier = JWT.require(algorithm) .build(); DecodedJWT jwt =
	 * verifier.verify(token); return jwt.getClaim("emailid").asString(); } catch
	 * (JWTVerificationException exception) { exception.printStackTrace(); //log
	 * Token Verification Failed return null; }
	 */
}
