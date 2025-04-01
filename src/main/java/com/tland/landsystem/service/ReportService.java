package com.tland.landsystem.service;

import com.tland.landsystem.dto.ReportDTO;
import com.tland.landsystem.entity.Job;
import com.tland.landsystem.entity.Land;
import com.tland.landsystem.entity.Report;
import com.tland.landsystem.entity.Users;
import com.tland.landsystem.repository.JobRepository;
import com.tland.landsystem.repository.LandRepository;
import com.tland.landsystem.repository.ReportRepository;
import com.tland.landsystem.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private LandRepository landRepository;

    public List<ReportDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReportDTO> getReportsByUserId(Integer userId) {
        List<Report> reports = reportRepository.findByCreatedById(userId);
        return reports.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReportDTO> getReportById(Integer id) {
        return reportRepository.findById(id)
                .map(this::convertToDTO);
    }

    public boolean existsById(Integer id) {
        return reportRepository.existsById(id);
    }

    @Transactional
    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = new Report();
        report.setReportName(reportDTO.getReportName());
        report.setContent(reportDTO.getContent());
        report.setCreationDate(reportDTO.getReportDate());
        report.setAnalysisData(reportDTO.getAnalysisData());

        // Set image if provided
        if (reportDTO.getImageBytes() != null) {
            report.setImage(reportDTO.getImageBytes());
        }

        // Set created by user
        if (reportDTO.getCreatedById() != null) {
            Users createdBy = userRepository.findById(reportDTO.getCreatedById())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + reportDTO.getCreatedById()));
            report.setCreatedBy(createdBy);
        }

        // Set job if provided
        if (reportDTO.getJobId() != null) {
            Job job = jobRepository.findById(reportDTO.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found with ID: " + reportDTO.getJobId()));
            report.setJob(job);
        }

        // Set land if provided
        if (reportDTO.getLandId() != null) {
            Land land = landRepository.findById(reportDTO.getLandId())
                    .orElseThrow(() -> new RuntimeException("Land not found with ID: " + reportDTO.getLandId()));
            report.setLand(land);
        }

        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
    }

    @Transactional
    public ReportDTO updateReport(ReportDTO reportDTO) {
        Report report = reportRepository.findById(reportDTO.getId())
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + reportDTO.getId()));

        report.setReportName(reportDTO.getReportName());
        report.setContent(reportDTO.getContent());
        report.setAnalysisData(reportDTO.getAnalysisData());

        // Only update image if provided
        if (reportDTO.getImageBytes() != null) {
            report.setImage(reportDTO.getImageBytes());
        }

        // Update job if provided
        if (reportDTO.getJobId() != null) {
            Job job = jobRepository.findById(reportDTO.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found with ID: " + reportDTO.getJobId()));
            report.setJob(job);
        }

        // Update land if provided
        if (reportDTO.getLandId() != null) {
            Land land = landRepository.findById(reportDTO.getLandId())
                    .orElseThrow(() -> new RuntimeException("Land not found with ID: " + reportDTO.getLandId()));
            report.setLand(land);
        }

        Report updatedReport = reportRepository.save(report);
        return convertToDTO(updatedReport);
    }

    @Transactional
    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    private ReportDTO convertToDTO(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setId(report.getId());
        dto.setReportName(report.getReportName());
        dto.setReportDate(report.getCreationDate());
        dto.setContent(report.getContent());
        dto.setAnalysisData(report.getAnalysisData());

        // Include creator's information if available
        if (report.getCreatedBy() != null) {
            dto.setCreatedById(report.getCreatedBy().getId());
            dto.setCreatedByFullName(report.getCreatedBy().getFullName());
        }

        // Convert image to Base64 if exists
        if (report.getImage() != null && report.getImage().length > 0) {
            dto.setImage(Base64.getEncoder().encodeToString(report.getImage()));
        }

        // Add land information if available
        if (report.getLand() != null) {
            dto.setLandId(report.getLand().getId());
            dto.setLandLocation(report.getLand().getLocation());
        }

        // Add job information if available
        if (report.getJob() != null) {
            dto.setJobId(report.getJob().getId());
            if (report.getJob().getJobType() != null) {
                dto.setJobTypeName(report.getJob().getJobType().getName());
            }
        }

        return dto;
    }
}