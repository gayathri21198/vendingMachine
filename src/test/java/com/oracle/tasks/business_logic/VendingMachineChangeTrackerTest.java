package com.oracle.tasks.business_logic;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test Class for the VendingMachineChangeTracker Class
 */
class VendingMachineChangeTrackerTest {

    private VendingMachineChangeTracker vendingMachine;

    @BeforeEach
    void setUp() {
        List<Coin> coinDenominations = Arrays.asList(new Coin(1), new Coin(2), new Coin(5), new Coin(10));
        vendingMachine = new VendingMachineChangeTracker(coinDenominations);
    }

    @Test
    void testRegisterCoins() {
        List<Coin> coins = Arrays.asList(new Coin(1), new Coin(2), new Coin(2), new Coin(5), new Coin(10));
        vendingMachine.registerCoins(coins);
        assertEquals(coins, vendingMachine.getCoinPool());
    }

    @Test
    void testGetChange() throws ExactChangeNotAvailableException {
        List<Coin> coins = Arrays.asList(new Coin(1), new Coin(2), new Coin(2), new Coin(5), new Coin(10));
        vendingMachine.registerCoins(coins);

        int orderValue = 7;
        List<Coin> expectedChange = Arrays.asList(new Coin(2), new Coin(5));
        assertEquals(expectedChange.size(), vendingMachine.getChange(orderValue).size());
    }

    @Test
    void testGetChangeExactChangeNotAvailable() {
        List<Coin> coins = Arrays.asList(new Coin(1), new Coin(2), new Coin(5), new Coin(10));
        vendingMachine.registerCoins(coins);

        int orderValue = 9;
        assertThrows(ExactChangeNotAvailableException.class, () -> vendingMachine.getChange(orderValue));
    }

    @Test
    void testGetChangeNoCoinsRegistered() {
        int orderValue = 7;
        assertThrows(ExactChangeNotAvailableException.class, () -> vendingMachine.getChange(orderValue));
    }
}
