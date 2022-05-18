package com.revature.TeamCP2.beans.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.revature.TeamCP2.beans.services.BCryptHash.class)
public class BCryptHashTest {

    @Test
    public void hashSuccessfullyReturnsString(@Autowired BCryptHash bCryptHash) {
        String passwordToHash = "myOtherPassword";

        String hashedPassword = bCryptHash.hash(passwordToHash);

        assertNotEquals(hashedPassword, passwordToHash);
        assertNotNull(hashedPassword);
    }

    @Test
    public void verifyReturnsTrueOnMatchedPassword(@Autowired BCryptHash bCryptHash) {
        String unhashed = "un-hashed string";
        String hashed = BCrypt.withDefaults().hashToString(12, unhashed.toCharArray());

        boolean matches = bCryptHash.verify(unhashed, hashed);

        assertTrue(matches);
    }
}
