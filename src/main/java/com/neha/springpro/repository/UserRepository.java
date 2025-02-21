package com.neha.springpro.repository;

import com.neha.springpro.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by email using JPQL
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    // Fetch all users sorted by name using JPQL
    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    List<User> findAllUsersSortedByName();

    // Fetch users with pagination and sorting
    @Query("SELECT u FROM User u")
    Page<User> findAllUsersWithPagination(Pageable pageable);
}
