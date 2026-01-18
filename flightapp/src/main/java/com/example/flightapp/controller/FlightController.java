package com.example.flightapp.controller;


import com.example.flightapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    //Get Lowest
    @GetMapping("/lowest-price-chicago")
    public ResponseEntity<Map<String, Object>> getLowestPriceToChicago(){
        Map<String, Object> result = flightService.getLowestPriceToChicago();
        return ResponseEntity.ok(result);
    }

    // Question 2: GET afternoon flight price
    @GetMapping("/afternoon-flight")
    public ResponseEntity<Map<String, Object>> getAfternoonFlightPrice() {
        Map<String, Object> result = flightService.getAfternoonFlightPrice();
        return ResponseEntity.ok(result);
    }

    // Question 3: GET price to JFK
    @GetMapping("/price-to-jfk")
    public ResponseEntity<Map<String, Object>> getPriceToJFK() {
        Map<String, Object> result = flightService.getPriceToJFK();
        return ResponseEntity.ok(result);
    }

    // Get all flights
    @GetMapping("/all")
    public ResponseEntity<?> getAllFlights() {
        return ResponseEntity.ok(flightService.getFlights());
    }
}
