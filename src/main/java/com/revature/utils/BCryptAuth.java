package com.revature.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;

public class BCryptAuth {

    private BCryptAuth(){}

    /**
     * Takes in a string and returns the hash value.
     * @param strToHash The
     */
    public static String hash(String strToHash){
        return BCrypt.withDefaults().hashToString(12, strToHash.toCharArray());
    }

    /**
     * Compares a string to the hashed value.
     * @param strToCheck The unhashed string to check against hashedValue
     * @param hashedValue Hashed string to compare
     */
    public static Boolean verify(String strToCheck, String hashedValue){
        Result result = BCrypt.verifyer().verify(strToCheck.toCharArray(), hashedValue);
        return result.verified;
    }
}

