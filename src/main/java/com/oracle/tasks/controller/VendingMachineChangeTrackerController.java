package com.oracle.tasks.controller;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import com.oracle.tasks.service.VendingMachineChangeTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller Class that will house all the Vending Machine Change Tracker Module API-Endpoints
 * */
@RestController
public class VendingMachineChangeTrackerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineChangeTrackerController.class);
    @Autowired
    private VendingMachineChangeTrackerService vendingMachineChangeTrackerService;

    @PostMapping("/api/v1/vending-machine/initialize")
    public ResponseEntity initializeVendingMachine(@RequestBody List<Coin> coinDenominations) {
        LOGGER.info("VendingMachineChangeTrackerController - initializeVendingMachine method invoked");
        vendingMachineChangeTrackerService.initializeVendingMachine(coinDenominations);
        return ResponseEntity.status(HttpStatus.OK).body("Vending Machine Initialised successfully");
    }

    @PostMapping("/api/v1/vending-machine/register-coins")
    public ResponseEntity registerCoins(@RequestBody List<Coin> coins) {
        LOGGER.info("VendingMachineChangeTrackerController - registerCoins method invoked");
        vendingMachineChangeTrackerService.registerCoins(coins);
        return ResponseEntity.status(HttpStatus.OK).body("Vending Machine Coins Registered successfully");
    }

    @PostMapping("/api/v1/vending-machine/get-change")
    public ResponseEntity<List<Coin>> getChange(@RequestBody int orderValue) throws ExactChangeNotAvailableException {
        LOGGER.info("VendingMachineChangeTrackerController - getChange method invoked");
        List<Coin> change = vendingMachineChangeTrackerService.getChange(orderValue);
        return ResponseEntity.status(HttpStatus.OK).body(change);
    }
    @GetMapping("/api/v1/vending-machine")
    public ResponseEntity getVendingMachineChangeTrackerVersion() {
        LOGGER.info("VendingMachineChangeTrackerController - getVendingMachineChangeTrackerVersion method invoked");
        return ResponseEntity.status(HttpStatus.OK).body("Version 1.0 | Vending Machine Change Tracker");
    }
}
