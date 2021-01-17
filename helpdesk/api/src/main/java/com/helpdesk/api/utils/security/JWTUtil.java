package com.helpdesk.api.utils.security;

import java.time.Instant;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.helpdesk.api.dto.UserAuthenticationTokenDTO;
import com.helpdesk.api.exceptions.JWTDecodingException;


public abstract class JWTUtil {

    private static final String JWT_ISSUER                          = "helpdesk-api";
    private static final long JWT_VALIDITY_DURATION_IN_SECONDS      = 60 * 60 * 8;
    private static final String JWT_CLAIM_PRINCIPAL                 = "principal";
    private static final String JWT_CLAIM_EMAIL                     = "email";
    private static final String JWT_CLAIM_ROLE                      = "role";
    private static final String JWT_SECRET                          = "secret";

    private JWTUtil() {

    }

    public static String createJWT(String userId, String principal, String email, String role) {

        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(JWT_VALIDITY_DURATION_IN_SECONDS)))
                .withSubject(userId)
                .withClaim(JWT_CLAIM_PRINCIPAL, principal)
                .withClaim(JWT_CLAIM_EMAIL, email)
                .withClaim(JWT_CLAIM_ROLE, role)
                .sign(getAlgorithm());

    }

    public static UserAuthenticationTokenDTO decodeJWT(String jwt) {

        DecodedJWT decodedJWT;

        try {

            decodedJWT = JWT.require(getAlgorithm())
                .build()
                .verify(jwt);

        } catch(JWTVerificationException e) {
            throw new JWTDecodingException("Invalid authorization token", e);
        }

        return new UserAuthenticationTokenDTO(
                decodedJWT.getSubject(),
                decodedJWT.getClaim(JWT_CLAIM_PRINCIPAL).asString(),
                decodedJWT.getClaim(JWT_CLAIM_EMAIL).asString(),
                decodedJWT.getClaim(JWT_CLAIM_ROLE).asString());

    }

    private static Algorithm getAlgorithm() {
        return Algorithm.HMAC512(JWT_SECRET);
    }

}
