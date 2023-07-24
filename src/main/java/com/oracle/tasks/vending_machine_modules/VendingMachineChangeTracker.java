package com.oracle.tasks.vending_machine_modules;

import com.oracle.tasks.model.Coin;

import java.util.List;
import java.util.Map;

/**
 * Vending Machine Change Tracker class includes the business logic implementation
 */
public class VendingMachineChangeTracker {
    private final List<Coin> floatCoins;
    private Map<Integer, Integer> coinInventory;

    public Map<Integer, Integer> getCoinInventory() {
        return coinInventory;
    }

    public VendingMachineChangeTracker(List<Coin> floatCoins) {
        this.floatCoins = floatCoins;
    }

    public void registerCoins(List<Coin> coins) {
    }

    public List<Coin> getChange(int orderValue) {
        return null;
    }
}
