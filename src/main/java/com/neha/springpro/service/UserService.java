package com.neha.springpro.service;

import com.neha.springpro.model.User;
import com.neha.springpro.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor-based Dependency Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch all users sorted by name
    public List<User> getAllUsersSortedByName() {
        return userRepository.findAllUsersSortedByName();
    }

    // Fetch paginated & sorted users
    public Page<User> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAllUsersWithPagination(pageable);
    }

    // Fetch user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Fetch user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id); // Ensure the ID remains unchanged
            return userRepository.save(updatedUser);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }
}
