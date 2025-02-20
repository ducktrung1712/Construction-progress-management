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

    // Lấy danh sách tất cả các đất
    @GetMapping
    public List<Land> getAllLands() {
        return landService.getAllLands();
    }

    // Lấy thông tin một đất theo id
    @GetMapping("/{id}")
    public ResponseEntity<Land> getLandById(@PathVariable int id) {
        return landService.getLandById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới một đất
    @PostMapping
    public Land createLand(@RequestBody Land land) {
        return landService.createLand(land);
    }

    // Cập nhật thông tin một đất theo id
    @PutMapping("/{id}")
    public ResponseEntity<Land> updateLand(@PathVariable int id, @RequestBody Land landDetails) {
        return landService.updateLand(id, landDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa một đất theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLand(@PathVariable int id) {
        return landService.deleteLand(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
