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
    private final JobRepository jobRepository;
    @Autowired
    private LandRepository landRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Lấy tất cả công việc
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream().map(JobDTO::new).collect(Collectors.toList());
    }

    // Lấy công việc theo ID
    public JobDTO getJobById(Integer id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.map(JobDTO::new).orElse(null);
    }

    // Chuyển từ DTO sang Entity
    private Job convertToEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setDescription(jobDTO.getDescription());
        job.setStatus(JobStatus.fromString(jobDTO.getStatus()));

        // Kiểm tra dữ liệu ảnh có hợp lệ không
        if (jobDTO.getImage() != null && jobDTO.getImage().length > 0) {
            job.setImage(jobDTO.getImage()); // Giữ nguyên kiểu byte[]
        }

        if (jobDTO.getLandId() != null) {
            job.setLand(landRepository.findById(jobDTO.getLandId()).orElse(null));
        }

        if (jobDTO.getJobTypeId() != null) {
            job.setJobType(jobTypeRepository.findById(jobDTO.getJobTypeId()).orElse(null));
        }

        if (jobDTO.getAssignedTo() != null) {
            job.setAssignedTo(userRepository.findById(jobDTO.getAssignedTo()).orElse(null));
        }

        return job;
    }


    // Tạo công việc mới
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = convertToEntity(jobDTO);
        Job savedJob = jobRepository.save(job);
        return new JobDTO(savedJob);
    }

    public JobDTO updateJob(Integer id, JobDTO jobDTO) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (!jobOptional.isPresent()) {
            return null;
        }

        Job job = jobOptional.get();

        // Gán đối tượng Land
        if (jobDTO.getLandId() != null) {
            job.setLand(landRepository.findById(jobDTO.getLandId()).orElse(null));
        }

        // Gán đối tượng JobType
        if (jobDTO.getJobTypeId() != null) {
            job.setJobType(jobTypeRepository.findById(jobDTO.getJobTypeId()).orElse(null));
        }

        // Gán đối tượng AssignedTo (User)
        if (jobDTO.getAssignedTo() != null) {
            job.setAssignedTo(userRepository.findById(jobDTO.getAssignedTo()).orElse(null));
        }

        // Cập nhật các thuộc tính khác
        job.setDescription(jobDTO.getDescription());
        // Kiểm tra dữ liệu ảnh có hợp lệ không
        if (jobDTO.getImage() != null && jobDTO.getImage().length > 0) {
            job.setImage(jobDTO.getImage()); // Giữ nguyên kiểu byte[]
        }
        job.setStatus(JobStatus.fromString(jobDTO.getStatus())); // Chuyển String -> Enum

        Job updatedJob = jobRepository.save(job);
        return new JobDTO(updatedJob);
    }


    // Xóa công việc
    public boolean deleteJob(Integer id) {
        if (!jobRepository.existsById(id)) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }
}
