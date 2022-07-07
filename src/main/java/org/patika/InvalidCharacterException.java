package org.patika;

public class InvalidCharacterException extends Exception {
    public InvalidCharacterException(char c) {
        super( "Invalid board character: " + c);
    }
}
