package com.tland.landsystem.controller;

import com.tland.landsystem.entity.JobType;
import com.tland.landsystem.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobtypes")
public class JobTypeController {

    private final JobTypeService jobTypeService;

    @Autowired
    public JobTypeController(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    // Lấy danh sách tất cả jobtype
    @GetMapping
    public List<JobType> getAllJobTypes() {
        return jobTypeService.getAllJobTypes();
    }

    // Lấy chi tiết jobtype theo id
    @GetMapping("/{id}")
    public ResponseEntity<JobType> getJobTypeById(@PathVariable Integer id) {
        return jobTypeService.getJobTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới jobtype
    @PostMapping
    public ResponseEntity<JobType> createJobType(@RequestBody JobType jobType) {
        return ResponseEntity.ok(jobTypeService.createJobType(jobType));
    }

    // Cập nhật jobtype
    @PutMapping("/{id}")
    public ResponseEntity<JobType> updateJobType(@PathVariable Integer id, @RequestBody JobType jobTypeDetails) {
        return jobTypeService.updateJobType(id, jobTypeDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa jobtype
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobType(@PathVariable Integer id) {
        if (jobTypeService.deleteJobType(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}