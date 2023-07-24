package com.oracle.tasks.service;

import com.oracle.tasks.model.Coin;

import java.util.List;

/**
 * Interface with the Vending Machine Change Tracker Module methods
 * */
public interface VendingMachineChangeTrackerService {
    void initializeVendingMachine(List<Coin> floatCoins);
    void registerCoins(List<Coin> coins);
    List<Coin> getChange(int orderValue);
}
