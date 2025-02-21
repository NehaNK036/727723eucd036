package com.neha.springpro.controller;

import com.neha.springpro.model.Bus;
import com.neha.springpro.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // Get all buses
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    // Get buses with pagination and sorting
    @GetMapping("/paginated")
    public ResponseEntity<Page<Bus>> getBusesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "busNumber") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<Bus> buses = busService.getBusesWithPaginationAndSorting(page, size, sortBy, direction);
        return ResponseEntity.ok(buses);
    }

    // Add a new bus
    @PostMapping
    public ResponseEntity<?> addBus(@Valid @RequestBody Bus bus) {
        try {
            Bus newBus = busService.addBus(bus);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Something went wrong. Please try again."));
        }
    }

    // Get bus by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBusById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(busService.getBusById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Update the bus
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBus(@PathVariable Long id, @Valid @RequestBody Bus updatedBus) {
        try {
            Bus bus = busService.updateBus(id, updatedBus);
            return ResponseEntity.ok(bus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Could not update the bus. Please try again."));
        }
    }

    // Delete a bus
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable Long id) {
        try {
            busService.deleteBus(id);
            return ResponseEntity.ok(Map.of("message", "Bus deleted successfully."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
