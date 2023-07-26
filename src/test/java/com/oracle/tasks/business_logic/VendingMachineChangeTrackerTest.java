package com.oracle.tasks.business_logic;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test Class for the VendingMachineChangeTracker Class
 */
class VendingMachineChangeTrackerTest {
    private VendingMachineChangeTracker vendingMachine;

    @BeforeEach
    void setUp() {
        List<Coin> coinPool = Arrays.asList(
                new Coin(1),
                new Coin(5),
                new Coin(10),
                new Coin(25),
                new Coin(50)
        );
        vendingMachine = new VendingMachineChangeTracker(coinPool);
    }

    @Test
    void registerCoins() {
        List<Coin> coinsToRegister = Arrays.asList(new Coin(5), new Coin(10));
        vendingMachine.registerCoins(coinsToRegister);

        List<Integer> expectedCoinPool = Arrays.asList(
                new Coin(1),
                new Coin(5),
                new Coin(10),
                new Coin(25),
                new Coin(50),
                new Coin(5),
                new Coin(10)
        ).stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());

        Assertions.assertEquals(expectedCoinPool, vendingMachine.getCoinPool().stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList()));
    }

    @Test
    void getChange() throws ExactChangeNotAvailableException {
        int orderValue = 30;
        List<Integer> expectedChange = Arrays.asList(new Coin(5), new Coin(25)).stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());

        List<Integer> actualChange = vendingMachine.getChange(orderValue).stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());

        Assertions.assertEquals(expectedChange, actualChange);
    }

    @Test
    void getChangeExactChangeNotAvailable() {
        int orderValue = 378;

        Assertions.assertThrows(ExactChangeNotAvailableException.class, () -> vendingMachine.getChange(orderValue));
    }
}

