package com.tland.landsystem.controller;

import com.tland.landsystem.entity.Land;
import com.tland.landsystem.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lands")
public class LandController {

    @Autowired
    private LandService landService;

    @GetMapping
    public ResponseEntity<List<Land>> getAllLands() {
        return ResponseEntity.ok(landService.getAllLands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Land> getLandById(@PathVariable Integer id) {
        return ResponseEntity.ok(landService.getLandById(id));
    }

    @PostMapping
    public ResponseEntity<Land> createLand(@RequestBody Land land) {
        return ResponseEntity.ok(landService.createLand(land));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Land> updateLand(@PathVariable Integer id, @RequestBody Land landDetails) {
        return ResponseEntity.ok(landService.updateLand(id, landDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLand(@PathVariable Integer id) {
        landService.deleteLand(id);
        return ResponseEntity.noContent().build();
    }
}
