package org.monopoly.Exceptions;

/**
 * Player goes broke
 */
public class BankruptcyException extends Exception {
    public BankruptcyException(String message) {
        super(message);
    }
}
