package com.tland.landsystem.controller;

import com.tland.landsystem.dto.JobDTO;
import com.tland.landsystem.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Integer id) {
        JobDTO jobDTO = jobService.getJobById(id);
        return (jobDTO != null) ? ResponseEntity.ok(jobDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        JobDTO createdJob = jobService.createJob(jobDTO);
        return ResponseEntity.ok(createdJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Integer id, @RequestBody JobDTO jobDTO) {
        JobDTO updatedJob = jobService.updateJob(id, jobDTO);
        return (updatedJob != null) ? ResponseEntity.ok(updatedJob) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        boolean deleted = jobService.deleteJob(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
