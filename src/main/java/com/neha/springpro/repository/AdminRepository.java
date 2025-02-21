package com.neha.springpro.repository;

import com.neha.springpro.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    
    // Add pagination and sorting support
    Page<Admin> findAll(Pageable pageable);
}
