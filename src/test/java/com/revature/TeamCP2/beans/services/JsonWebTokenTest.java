package com.revature.TeamCP2.beans.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.NotAuthorizedException;
import com.revature.TeamCP2.interfaces.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class JsonWebTokenTest {

    static User USER_1;
    static Algorithm algorithm;
    @Mock MyJWTVerifier verifierMock;
    @Mock MyClaim claimMock;
    @Mock JWTDecoder jwtDecoderMock;
    @MockBean private ObjectMapper objectMapperMock;

    @BeforeAll
    public static void beforeAll() {
        USER_1 = new User();
        USER_1.setFirstName("John");
        USER_1.setLastName("Jacobs");
        USER_1.setRole(Role.USER);
        USER_1.setId(3);

        algorithm = Algorithm.HMAC512("secretString");
    }

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    public void verifyReturnsCookieDto(@Autowired JsonWebToken jsonWebToken) throws JsonProcessingException, NotAuthorizedException {
        jsonWebToken.setVerifier(verifierMock);
        String jwt = "312009802398023";
        String json = "Json Response";
        CookieDto cookieDto = new CookieDto(USER_1);
        when(objectMapperMock.readValue(anyString(), (Class<Object>) any())).thenReturn(cookieDto);
        when(verifierMock.verify(jwt)).thenReturn(jwtDecoderMock);
        when(jwtDecoderMock.getClaim("Json")).thenReturn(claimMock);
        when(claimMock.asString()).thenReturn(json);

        CookieDto returnedCookieDto = jsonWebToken.verify(jwt);

        assertEquals(returnedCookieDto, cookieDto);
        verify(objectMapperMock, times(1)).readValue(anyString(), (Class<Object>) any());
        verify(jwtDecoderMock, times(1)).getClaim("Json");
        verify(verifierMock, times(1)).verify(jwt);
    }

    @Test
    public void verifyThrowsNotAuthorizedException(@Autowired JsonWebToken jsonWebToken) throws JsonProcessingException, NotAuthorizedException {
        jsonWebToken.setVerifier(verifierMock);
        String jwt = "312009802398023";
        CookieDto cookieDto = new CookieDto(USER_1);
        when(objectMapperMock.readValue(anyString(), (Class<Object>) any())).thenReturn(cookieDto);
        when(verifierMock.verify(jwt)).thenThrow(JWTVerificationException.class);

        assertThrows(NotAuthorizedException.class, () -> jsonWebToken.verify(jwt));
        verify(jwtDecoderMock, times(0)).getClaim(any());
        verify(objectMapperMock, times(0)).readValue(anyString(), (Class<Object>) any());
        verify(jwtDecoderMock, times(0)).getClaim("Json");
        verify(verifierMock, times(1)).verify(jwt);
    }

    @Test
    public void signReturnsCookieDto(@Autowired JsonWebToken jsonWebToken) throws JsonProcessingException {
        CookieDto cookieDto = new CookieDto(USER_1);

        String jwt = jsonWebToken.sign(cookieDto);

        assertNotNull(jwt);
        verify(objectMapperMock, times(1)).writeValueAsString(cookieDto);
    }
    @Test
    public void signThrowsJWTCreationException(@Autowired JsonWebToken jsonWebToken) throws JsonProcessingException {
        CookieDto cookieDto = new CookieDto(USER_1);
        when(objectMapperMock.writeValueAsString(cookieDto)).thenThrow(JsonProcessingException.class);

        assertThrows(JWTCreationException.class, () -> jsonWebToken.sign(cookieDto));
    }

}

// Classes used for mocks
class JWTDecoder implements DecodedJWT {
    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getHeader() {
        return null;
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public String getSignature() {
        return null;
    }

    @Override
    public String getAlgorithm() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public String getKeyId() {
        return null;
    }

    @Override
    public Claim getHeaderClaim(String s) {
        return null;
    }

    @Override
    public String getIssuer() {
        return null;
    }

    @Override
    public String getSubject() {
        return null;
    }

    @Override
    public List<String> getAudience() {
        return null;
    }

    @Override
    public Date getExpiresAt() {
        return null;
    }

    @Override
    public Date getNotBefore() {
        return null;
    }

    @Override
    public Date getIssuedAt() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Claim getClaim(String s) {
        return null;
    }

    @Override
    public Map<String, Claim> getClaims() {
        return null;
    }
}

class MyClaim implements Claim {

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Boolean asBoolean() {
        return null;
    }

    @Override
    public Integer asInt() {
        return null;
    }

    @Override
    public Long asLong() {
        return null;
    }

    @Override
    public Double asDouble() {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public Date asDate() {
        return null;
    }

    @Override
    public <T> T[] asArray(Class<T> aClass) throws JWTDecodeException {
        return null;
    }

    @Override
    public <T> List<T> asList(Class<T> aClass) throws JWTDecodeException {
        return null;
    }

    @Override
    public Map<String, Object> asMap() throws JWTDecodeException {
        return null;
    }

    @Override
    public <T> T as(Class<T> aClass) throws JWTDecodeException {
        return null;
    }
}

class MyJWTVerifier implements com.auth0.jwt.interfaces.JWTVerifier {

    @Override
    public DecodedJWT verify(String s) throws JWTVerificationException {
        return null;
    }

    @Override
    public DecodedJWT verify(DecodedJWT decodedJWT) throws JWTVerificationException {
        return null;
    }
}