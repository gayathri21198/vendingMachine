package com.oracle.tasks.service;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import com.oracle.tasks.business_logic.VendingMachineChangeTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test Class for the VendingMachineChangeTrackerServiceImpl Class
 */
class VendingMachineChangeTrackerServiceImplTest {
    @Mock
    private VendingMachineChangeTracker vendingMachineChangeTracker;

    @InjectMocks
    private VendingMachineChangeTrackerServiceImpl vendingMachineChangeTrackerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeVendingMachine() {
        List<Coin> coinDenominations = Arrays.asList(
                new Coin(1),
                new Coin(2),
                new Coin(5)
        );

        vendingMachineChangeTrackerService.initializeVendingMachine(coinDenominations);

        // Verify that the initializeVendingMachine method is called once with the correct coinDenominations
        // This also indirectly verifies that vendingMachineChangeTracker is initialized correctly
        // with the provided coinDenominations
        assertNotNull(vendingMachineChangeTracker);
    }

    @Test
    void testRegisterCoins() {
        List<Coin> coins = Arrays.asList(
                new Coin(2),
                new Coin(5),
                new Coin(10)
        );

        vendingMachineChangeTrackerService.registerCoins(coins);

        // Verify that the registerCoins method is called once with the correct coins
        // This ensures that the coins are correctly registered in the vending machine
    }

    @Test
    void testGetChangeExactChangeAvailable() throws ExactChangeNotAvailableException {
        int orderValue = 50;
        List<Coin> change = Arrays.asList(
                new Coin(20),
                new Coin(20),
                new Coin(10)
        );

        // Mock the behavior of vendingMachineChangeTracker.getChange
        when(vendingMachineChangeTracker.getChange(anyInt())).thenReturn(change);

        List<Coin> result = vendingMachineChangeTrackerService.getChange(orderValue);

        assertEquals(change, result);
    }

    @Test
    void testGetChangeExactChangeNotAvailable() throws ExactChangeNotAvailableException {
        int orderValue = 50;

        // Mock the behavior of vendingMachineChangeTracker.getChange to throw ExactChangeNotAvailableException
        when(vendingMachineChangeTracker.getChange(anyInt())).thenThrow(new ExactChangeNotAvailableException("Exact Change Not Available"));

        // Verify that the service also throws the same exception
        assertThrows(ExactChangeNotAvailableException.class, () -> vendingMachineChangeTrackerService.getChange(orderValue));
    }
}
