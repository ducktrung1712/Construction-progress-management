package com.tland.landsystem.service;

import com.tland.landsystem.Enum.JobStatus;
import com.tland.landsystem.dto.JobDTO;
import com.tland.landsystem.entity.Job;
import com.tland.landsystem.repository.JobRepository;
import com.tland.landsystem.repository.JobTypeRepository;
import com.tland.landsystem.repository.LandRepository;
import com.tland.landsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private LandRepository landRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;
    @Autowired
    private UsersRepository userRepository;

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream().map(JobDTO::new).collect(Collectors.toList());
    }

    public JobDTO getJobById(Integer id) {
        return jobRepository.findById(id).map(JobDTO::new).orElse(null);
    }

    private Job convertToEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setDescription(jobDTO.getDescription());
        job.setStatus(JobStatus.fromString(jobDTO.getStatus()));
        job.setLand(jobDTO.getLandId() != null ? landRepository.findById(jobDTO.getLandId()).orElse(null) : null);
        job.setJobType(jobDTO.getJobTypeId() != null ? jobTypeRepository.findById(jobDTO.getJobTypeId()).orElse(null) : null);
        job.setAssignedTo(jobDTO.getAssignedTo() != null ? userRepository.findById(jobDTO.getAssignedTo()).orElse(null) : null);
        return job;
    }

    public JobDTO createJob(JobDTO jobDTO) {
        return new JobDTO(jobRepository.save(convertToEntity(jobDTO)));
    }

    public JobDTO updateJob(Integer id, JobDTO jobDTO) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (!jobOptional.isPresent()) {
            return null;
        }
        Job job = jobOptional.get();
        job.setLand(jobDTO.getLandId() != null ? landRepository.findById(jobDTO.getLandId()).orElse(null) : null);
        job.setJobType(jobDTO.getJobTypeId() != null ? jobTypeRepository.findById(jobDTO.getJobTypeId()).orElse(null) : null);
        job.setAssignedTo(jobDTO.getAssignedTo() != null ? userRepository.findById(jobDTO.getAssignedTo()).orElse(null) : null);
        job.setDescription(jobDTO.getDescription());
        job.setStatus(JobStatus.fromString(jobDTO.getStatus()));
        return new JobDTO(jobRepository.save(job));
    }

    public boolean deleteJob(Integer id) {
        if (!jobRepository.existsById(id)) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    public List<JobDTO> getJobsByAssignedUserId(Integer userId) {
        return jobRepository.findByAssignedToId(userId).stream().map(JobDTO::new).collect(Collectors.toList());
    }
}