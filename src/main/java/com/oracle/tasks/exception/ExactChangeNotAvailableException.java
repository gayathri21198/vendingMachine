package com.oracle.tasks.exception;

/**
 * Custom Exception to raise when the exact change is not available
 */
public class ExactChangeNotAvailableException extends Exception {
    public ExactChangeNotAvailableException(String message) {
        super(message);
    }
}
