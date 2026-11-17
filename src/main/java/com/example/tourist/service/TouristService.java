package com.example.tourist.service;

import com.example.tourist.entity.Tourist;
import com.example.tourist.repository.TouristRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<Tourist> fetchAll() {
        return repository.findAll();
    }

    public Tourist updateTourist(Long id, Tourist newTourist) {

        Tourist existing = repository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Tourist not found"));

        existing.setName(newTourist.getName());
        existing.setEmail(newTourist.getEmail());
        existing.setDestination(newTourist.getDestination());

        return repository.save(existing);
    }
}