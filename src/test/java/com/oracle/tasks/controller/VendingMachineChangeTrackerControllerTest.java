package com.oracle.tasks.controller;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import com.oracle.tasks.service.VendingMachineChangeTrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Test Class for the VendingMachineChangeTrackerController Class
 */
class VendingMachineChangeTrackerControllerTest {

    @Mock
    private VendingMachineChangeTrackerService vendingMachineChangeTrackerService;

    @InjectMocks
    private VendingMachineChangeTrackerController vendingMachineChangeTrackerController;

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

        ResponseEntity response = vendingMachineChangeTrackerController.initializeVendingMachine(coinDenominations);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vending Machine Initialised successfully", response.getBody());
    }

    @Test
    void testRegisterCoins() {
        List<Coin> coins = Arrays.asList(
                new Coin(2),
                new Coin(5),
                new Coin(10)
        );

        ResponseEntity response = vendingMachineChangeTrackerController.registerCoins(coins);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vending Machine Coins Registered successfully", response.getBody());
    }

    @Test
    void testGetChangeExactChangeAvailable() throws ExactChangeNotAvailableException {
        int orderValue = 50;
        List<Coin> change = Arrays.asList(
                new Coin(20),
                new Coin(20),
                new Coin(10)
        );

        // Mock the behavior of vendingMachineChangeTrackerService.getChange
        when(vendingMachineChangeTrackerService.getChange(anyInt())).thenReturn(change);

        ResponseEntity<List<Coin>> response = vendingMachineChangeTrackerController.getChange(orderValue);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(change, response.getBody());
    }

    @Test
    void testGetVendingMachineChangeTrackerVersion() {
        ResponseEntity response = vendingMachineChangeTrackerController.getVendingMachineChangeTrackerVersion();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Version 1.0 | Vending Machine Change Tracker", response.getBody());
    }
}
