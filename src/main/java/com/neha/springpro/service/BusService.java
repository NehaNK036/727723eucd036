package com.neha.springpro.service;

import com.neha.springpro.model.Bus;
import com.neha.springpro.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Page<Bus> getBusesWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return busRepository.findAll(pageable);
    }

    public Bus addBus(Bus bus) {
        Optional<Bus> existingBus = busRepository.findByBusNumber(bus.getBusNumber());
        if (existingBus.isPresent()) {
            throw new IllegalArgumentException("Bus with number " + bus.getBusNumber() + " already exists.");
        }
        return busRepository.save(bus);
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bus with ID " + id + " not found."));
    }

    public void deleteBus(Long id) {
        if (!busRepository.existsById(id)) {
            throw new IllegalArgumentException("Bus with ID " + id + " does not exist.");
        }
        busRepository.deleteById(id);
    }

    public Bus updateBus(Long id, Bus updatedBus) {
        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bus not found with id: " + id));

        existingBus.setBusNumber(updatedBus.getBusNumber());
        existingBus.setRoute(updatedBus.getRoute());
        existingBus.setCapacity(updatedBus.getCapacity());
        existingBus.setDriverName(updatedBus.getDriverName());

        return busRepository.save(existingBus);
    }
}
