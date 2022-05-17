package com.revature.TeamCP2.beans.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptHashTest {

    BCryptHash bCryptHash;

    @BeforeEach
    public void beforeEach() {
        bCryptHash = new BCryptHash();
    }

    @Test
    public void hashSuccessfullyReturnsString() {
        String passwordToHash = "myOtherPassword";

        String hashedPassword = bCryptHash.hash(passwordToHash);

        assertNotEquals(hashedPassword, passwordToHash);
        assertNotNull(hashedPassword);
    }

    @Test
    public void verifyReturnsTrueOnMatchedPassword() {
        String unhashed = "un-hashed string";
        String hashed = BCrypt.withDefaults().hashToString(12, unhashed.toCharArray());

        boolean matches = bCryptHash.verify(unhashed, hashed);

        assertTrue(matches);
    }
}
