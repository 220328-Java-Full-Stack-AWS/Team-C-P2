/**
 *
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: To create and verify a json web token.
 */
package com.revature.TeamCP2.beans.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.exceptions.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonWebToken {

    private Algorithm algorithm;
    private JWTVerifier verifier;
    private ObjectMapper objectMapper;
    @Value("secret")
    private String secret;

    @Autowired
    public JsonWebToken(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        algorithm =  Algorithm.HMAC512(secret);

        verifier = JWT.require(algorithm)
                .build();
    }

    /**
     * Turns JsonWebtoken string into CookieDto
     * @param jwt JsonWebtoken string to parse
     */
    public CookieDto verify(String jwt) throws NotAuthorizedException {
        try {
            DecodedJWT decodedString = verifier.verify(jwt);
            String json = decodedString.getClaim("Json").asString();

            return objectMapper.readValue(json, CookieDto.class);
        } catch (JWTVerificationException | JsonProcessingException e) {
            throw new NotAuthorizedException();
        }
    }

    /**
     * Turns CookieDto into JsonWebToken string
     * @param cookieDto Object to serialize
     */
    public String sign(CookieDto cookieDto) throws JWTCreationException {

        try {
            String jsonCookieDto = objectMapper.writeValueAsString(cookieDto);

            return JWT.create()
                    .withClaim("Json", jsonCookieDto)
                    .sign(algorithm);

        } catch (JsonProcessingException e) {
            throw new JWTCreationException("Processing exception", null);
        }
    }


}
