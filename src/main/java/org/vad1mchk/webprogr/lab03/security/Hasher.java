package org.vad1mchk.webprogr.lab03.security;

/**
 * Represents a hashing mechanism for hashing strings with optional salt values.
 */
public interface Hasher {
    /**
     * Hashes a given string without an additional salt.
     *
     * @param string The string to be hashed.
     * @return The hashed byte array.
     */
    byte[] hash(String string);

    /**
     * Hashes a given string with an additional salt.
     *
     * @param string The string to be hashed.
     * @param salt   The salt byte array to use in the hashing process.
     * @return The hashed byte array.
     */
    byte[] hash(String string, byte[] salt);

    /**
     * Generates a salt that can be used for hashing.
     *
     * @return A byte array representing the generated salt.
     */
    byte[] generateSalt();
}
