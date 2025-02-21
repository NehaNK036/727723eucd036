package com.neha.springpro.repository;

import com.neha.springpro.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // ✅ Find driver by license number
    Optional<Driver> findByLicenseNumber(String licenseNumber);

    // ✅ Find all drivers assigned to a specific bus
    @Query("SELECT d FROM Driver d WHERE d.assignedBus.id = :busId")
    List<Driver> findDriversByBusId(@Param("busId") Long busId);

    // ✅ Get all drivers sorted by name
    List<Driver> findAllByOrderByNameAsc();

    // ✅ Get all drivers with pagination
    Page<Driver> findAll(Pageable pageable);
}
