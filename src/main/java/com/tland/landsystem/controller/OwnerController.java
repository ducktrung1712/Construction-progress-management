package com.tland.landsystem.controller;

import com.tland.landsystem.entity.Owner;
import com.tland.landsystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Integer id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.createOwner(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Integer id, @RequestBody Owner ownerDetails) {
        return ResponseEntity.ok(ownerService.updateOwner(id, ownerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
