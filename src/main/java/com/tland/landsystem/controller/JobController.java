package com.tland.landsystem.controller;

import com.tland.landsystem.Entity.Job;
import com.tland.landsystem.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Lấy danh sách tất cả job
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Lấy chi tiết 1 job theo id
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        return jobOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        return ResponseEntity.ok(savedJob);
    }

    // Cập nhật job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer id, @RequestBody Job jobDetails) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (!jobOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Job job = jobOptional.get();
        job.setLand(jobDetails.getLand());
        job.setJobType(jobDetails.getJobType());
        job.setStatus(jobDetails.getStatus());
        job.setAssignedTo(jobDetails.getAssignedTo());
        job.setDescription(jobDetails.getDescription());
        job.setImage(jobDetails.getImage());
        Job updatedJob = jobRepository.save(job);
        return ResponseEntity.ok(updatedJob);
    }

    // Xóa job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
