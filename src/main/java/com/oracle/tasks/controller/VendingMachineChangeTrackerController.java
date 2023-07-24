package com.oracle.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class that will house all the Vending Machine Change Tracker Module API-Endpoints
 * */
@RestController
public class VendingMachineChangeTrackerController {
    @GetMapping("/api/v1/vending-machine")
    public ResponseEntity getVendingMachineChangeTrackerVersion() {
        return ResponseEntity.status(HttpStatus.OK).body("Version 1.0 | Vending Machine Change Tracker");
    }
}
