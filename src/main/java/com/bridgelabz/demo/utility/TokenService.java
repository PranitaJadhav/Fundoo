package com.bridgelabz.demo.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenService {

	// it is a random generated string

	public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";

	// create token based onHMAC256 claim:// allow us to add any data into tokenthis
	// data and this data can be fetched from token after decoding it

	public String createToken(String email) throws UnsupportedEncodingException {

		
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			String token = JWT.create().withClaim("email", email).sign(algorithm);
			return token;
		
			// log TokenSigning Failed
		
	}

	public String getUserToken(String token) {
		Claim claim = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaim("email");

		System.out.println("claim" + claim);
		return claim.asString();

	}
	
	
	public String decodetoken(String token) {
		String emailid;

		Verification verification = null;

		try {

			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		}

		JWTVerifier jwtverifier = verification.build();

		DecodedJWT decodejwt = jwtverifier.verify(token);

		Claim claim = decodejwt.getClaim("emailid");

		emailid = claim.asString();

		return emailid;
	}

	}





