package com.neha.springpro.service;

import com.neha.springpro.model.Driver;
import com.neha.springpro.repository.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // ✅ Add a new driver
    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // ✅ Get all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // ✅ Get drivers sorted by name
    public List<Driver> getDriversSortedByName() {
        return driverRepository.findAllByOrderByNameAsc();
    }

    // ✅ Get paginated & sorted drivers
    public Page<Driver> getDriversWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return driverRepository.findAll(pageable);
    }

    // ✅ Get driver by ID
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // ✅ Get driver by license number
    public Optional<Driver> getDriverByLicenseNumber(String licenseNumber) {
        return driverRepository.findByLicenseNumber(licenseNumber);
    }

    // ✅ Get all drivers assigned to a specific bus
    public List<Driver> getDriversByBusId(Long busId) {
        return driverRepository.findDriversByBusId(busId);
    }

    // ✅ Update driver with improved error handling
    public Driver updateDriver(Long id, Driver updatedDriver) {
        return driverRepository.findById(id).map(driver -> {
            if (updatedDriver.getName() != null) driver.setName(updatedDriver.getName());
            if (updatedDriver.getLicenseNumber() != null) driver.setLicenseNumber(updatedDriver.getLicenseNumber());
            if (updatedDriver.getPhone() != null) driver.setPhone(updatedDriver.getPhone());
            if (updatedDriver.getAssignedBus() != null) driver.setAssignedBus(updatedDriver.getAssignedBus());
            return driverRepository.save(driver);
        }).orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    // ✅ Delete driver
    public boolean deleteDriver(Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
