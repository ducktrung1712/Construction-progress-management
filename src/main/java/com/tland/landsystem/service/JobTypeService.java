package com.tland.landsystem.service;

import com.tland.landsystem.entity.JobType;
import com.tland.landsystem.repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTypeService {

    private final JobTypeRepository jobTypeRepository;

    @Autowired
    public JobTypeService(JobTypeRepository jobTypeRepository) {
        this.jobTypeRepository = jobTypeRepository;
    }

    public List<JobType> getAllJobTypes() {
        return jobTypeRepository.findAll();
    }

    public Optional<JobType> getJobTypeById(Integer id) {
        return jobTypeRepository.findById(id);
    }

    public JobType createJobType(JobType jobType) {
        return jobTypeRepository.save(jobType);
    }

    public Optional<JobType> updateJobType(Integer id, JobType jobTypeDetails) {
        return jobTypeRepository.findById(id).map(jobType -> {
            jobType.setName(jobTypeDetails.getName());
            jobType.setDescription(jobTypeDetails.getDescription());
            return jobTypeRepository.save(jobType);
        });
    }

    public boolean deleteJobType(Integer id) {
        if (jobTypeRepository.existsById(id)) {
            jobTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
