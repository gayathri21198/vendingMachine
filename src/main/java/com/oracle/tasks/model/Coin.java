package com.oracle.tasks.model;

/**
 * Coin class with coin specific attributes like value, currency
 */
public class Coin {
    private int value;
    private static final Currency currency = Currency.INR; // The current value can be maintained in properties files and loaded as application starts up. The underlying assumption is theVending Machine does not support multiple currencies

    public Coin() {}

    public Coin(int value) {
        this.value = value; // Value always set using Constructor
    }

    public int getValue() {
        return value;
    }
}
