package com.tland.landsystem.controller;

import com.tland.landsystem.entity.Area;
import com.tland.landsystem.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAllAreas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable int id) {
        Optional<Area> area = areaService.getAreaById(id);
        return area.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Area> createArea(@RequestBody Area area) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.createArea(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArea(@PathVariable int id, @RequestBody Area areaDetails) {
        try {
            return ResponseEntity.ok(areaService.updateArea(id, areaDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable int id) {
        try {
            areaService.deleteArea(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
