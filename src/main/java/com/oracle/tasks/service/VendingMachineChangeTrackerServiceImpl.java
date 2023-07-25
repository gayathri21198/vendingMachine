package com.oracle.tasks.service;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import com.oracle.tasks.business_logic.VendingMachineChangeTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Class that will invoke the Vending Machine Change Tracker Module methods
 * No Data Layer as there is no need to persist any data beyond storing it in memory and can assume that the vending machine software will run continuously
 * */
@Service
public class VendingMachineChangeTrackerServiceImpl implements VendingMachineChangeTrackerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineChangeTrackerServiceImpl.class);
    private VendingMachineChangeTracker vendingMachineChangeTracker;
    public void initializeVendingMachine(List<Coin> coinDenominations) {
        LOGGER.info("VendingMachineChangeTrackerServiceImpl - initializeVendingMachine method invoked");
        vendingMachineChangeTracker = new VendingMachineChangeTracker(coinDenominations);
    }

    public void registerCoins(List<Coin> coins) {
        LOGGER.info("VendingMachineChangeTrackerServiceImpl - registerCoins method invoked");
        vendingMachineChangeTracker.registerCoins(coins);
    }

    public List<Coin> getChange(int orderValue) throws ExactChangeNotAvailableException {
        LOGGER.info("VendingMachineChangeTrackerServiceImpl - getChange method invoked");
        return vendingMachineChangeTracker.getChange(orderValue);
    }
}
