package org.monopoly.Exceptions;

/**
 * Exception to handle when a house can't be built
 */
public class HotelCannotBeBuiltException extends Exception {
    public HotelCannotBeBuiltException(String message) {
        super(message);
    }
}
