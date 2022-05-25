/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Hashes a string & compares hashed string to an unhashed string for equality.
 */
package com.revature.TeamCP2.beans.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptHash {

    public BCryptHash() {}

    /**
     * Takes in an unhashed String and returns the hashed value.
     * @param string Value to hash
     */
    public String hash(String string){
        return BCrypt.withDefaults().hashToString(12, string.toCharArray());
    }

    /**
     * Method returns true or false if the arguments match.
     * @param string Plain text string
     * @param hashedString Hashed string to check {@code string} against.
     */
    public Boolean verify(String string, String hashedString){
        BCrypt.Result result = BCrypt.verifyer().verify(string.toCharArray(), hashedString);
        return result.verified;
    }
}
