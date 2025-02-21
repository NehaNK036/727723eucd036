package com.neha.springpro.repository;

import com.neha.springpro.model.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(String busNumber);
    
    // Add support for pagination and sorting
    Page<Bus> findAll(Pageable pageable);
}
