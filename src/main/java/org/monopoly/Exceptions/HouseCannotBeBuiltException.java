package org.monopoly.Exceptions;

/**
 * Exception to handle when a house can't be built
 */
public class HouseCannotBeBuiltException extends Exception {
    public HouseCannotBeBuiltException(String message) {
        super(message);
    }
}
