package com.tland.landsystem.controller;

import com.tland.landsystem.entity.WorkGroup;
import com.tland.landsystem.service.WorkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workgroups")
@CrossOrigin(origins = "*")
public class WorkGroupController {

    @Autowired
    private WorkGroupService workGroupService;

    // Lấy tất cả WorkGroups
    @GetMapping
    public ResponseEntity<List<WorkGroup>> getAllWorkGroups() {
        return ResponseEntity.ok(workGroupService.getAllWorkGroups());
    }

    // Lấy WorkGroup theo ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkGroup> getWorkGroupById(@PathVariable Integer id) {
        WorkGroup workGroup = workGroupService.getWorkGroupById(id);
        return workGroup != null ? ResponseEntity.ok(workGroup) : ResponseEntity.notFound().build();
    }

    // Tạo WorkGroup mới
    @PostMapping
    public ResponseEntity<WorkGroup> createWorkGroup(@RequestBody WorkGroup workGroup) {
        return ResponseEntity.ok(workGroupService.createWorkGroup(workGroup));
    }

    // Cập nhật WorkGroup
    @PutMapping("/{id}")
    public ResponseEntity<WorkGroup> updateWorkGroup(@PathVariable Integer id, @RequestBody WorkGroup workGroup) {
        WorkGroup updated = workGroupService.updateWorkGroup(id, workGroup);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa WorkGroup
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkGroup(@PathVariable Integer id) {
        return workGroupService.deleteWorkGroup(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
