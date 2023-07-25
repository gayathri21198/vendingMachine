package com.oracle.tasks.model;

/**
 * Coin class with coin specific attributes like value, currency
 */
public class Coin {
    private int value;
    private static final Currency currency = Currency.GBP; // The current value can be maintained in properties files and loaded as application starts up. The underlying assumption is Vending Machine does not support multiple currencies

    public Coin() {}

    public Coin(int value) {
        this.value = value; // Value always set using Constructor
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "value=" + value +
                '}';
    }
}
