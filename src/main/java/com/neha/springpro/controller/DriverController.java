package com.neha.springpro.controller;

import com.neha.springpro.model.Driver;
import com.neha.springpro.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // ✅ Add a new driver
    @PostMapping
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.addDriver(driver));
    }

    // ✅ Get all drivers
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    // ✅ Get paginated & sorted drivers
    @GetMapping("/paginated")
    public ResponseEntity<Page<Driver>> getDriversWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<Driver> drivers = driverService.getDriversWithPaginationAndSorting(page, size, sortBy, direction);
        return ResponseEntity.ok(drivers);
    }

    // ✅ Get driver by ID
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get driver by license number
    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<Driver> getDriverByLicenseNumber(@PathVariable String licenseNumber) {
        return driverService.getDriverByLicenseNumber(licenseNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all drivers by bus ID
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Driver>> getDriversByBusId(@PathVariable Long busId) {
        List<Driver> drivers = driverService.getDriversByBusId(busId);
        return drivers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(drivers);
    }

    // ✅ Update driver with better exception handling
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable Long id, @RequestBody Driver updatedDriver) {
        try {
            return ResponseEntity.ok(driverService.updateDriver(id, updatedDriver));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Delete driver
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long id) {
        return driverService.deleteDriver(id)
                ? ResponseEntity.ok("Driver deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
