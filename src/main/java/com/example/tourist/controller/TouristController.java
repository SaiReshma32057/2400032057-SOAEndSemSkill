
package com.example.tourist.controller;

import com.example.tourist.entity.Tourist;
import com.example.tourist.service.TouristService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final TouristService service;

    private static final String VALID_TOKEN = "my-secret-token";

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tourist>> fetchAll(
        @RequestHeader(value = "X-Custom-Token", required = false)
        String token) {

        if (token == null || !VALID_TOKEN.equals(token)) {
            throw new IllegalArgumentException(
                "unauthorised  invalid token provided");
        }

        return ResponseEntity.ok(service.fetchAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tourist> updateTourist(
        @PathVariable Long id,
        @RequestHeader(value = "token_val", required = false)
        String token,
        @RequestBody Tourist tourist) {

        if (token == null || !VALID_TOKEN.equals(token)) {
            throw new IllegalArgumentException(
                "unauthorised  invalid token provided");
        }

        Tourist updated = service.updateTourist(id, tourist);

        return ResponseEntity.ok(updated);
    }
}
