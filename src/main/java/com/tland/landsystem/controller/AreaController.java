package com.tland.landsystem.controller;

import com.tland.landsystem.Entity.Area;
import com.tland.landsystem.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    // Lấy danh sách tất cả các khu vực
    @GetMapping
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    // Lấy thông tin một khu vực theo id
    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable int id) {
        Optional<Area> area = areaRepository.findById(id);
        return area.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới một khu vực
    @PostMapping
    public Area createArea(@RequestBody Area area) {
        return areaRepository.save(area);
    }

    // Cập nhật thông tin một khu vực theo id
    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable int id, @RequestBody Area areaDetails) {
        Optional<Area> optionalArea = areaRepository.findById(id);
        if (optionalArea.isPresent()) {
            Area area = optionalArea.get();
            area.setName(areaDetails.getName());
            area.setTotalArea(areaDetails.getTotalArea());
            area.setPriorityLandType(areaDetails.getPriorityLandType());
            area.setImage(areaDetails.getImage());
            area.setDescription(areaDetails.getDescription());
            Area updatedArea = areaRepository.save(area);
            return ResponseEntity.ok(updatedArea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa một khu vực theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable int id) {
        if (areaRepository.existsById(id)) {
            areaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
