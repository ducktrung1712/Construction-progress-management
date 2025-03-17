package com.tland.landsystem.service;

import com.tland.landsystem.dto.ReportResponse;
import com.tland.landsystem.entity.Job;
import com.tland.landsystem.entity.Report;
import com.tland.landsystem.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<ReportResponse> getAllReports() {
        var reports = reportRepository.findAll();
        List<ReportResponse> responseList = new ArrayList<>();

        for (Report report : reports) {
            Job job = report.getJob();  // Kiểm tra job có null không
            Integer jobTypeId = (job != null && job.getJobType() != null) ? job.getJobType().getId() : null;
            String jobTypeName = (job != null && job.getJobType() != null) ? job.getJobType().getName() : "Không xác định";

            var response = new ReportResponse(
                    report.getId(),
                    report.getReportName(),
                    report.getCreationDate(),
                    report.getCreatedBy(),
                    report.getContent(),
                    report.getAnalysisData(),
                    report.getJob(),
                    report.getLand(),
                    jobTypeId,  // Thêm jobTypeId vào response
                    jobTypeName // Thêm jobTypeName vào response
            );
            responseList.add(response);
        }
        return responseList;
    }


    public List<ReportResponse> getReportsByJobType(Integer jobTypeId) {
        return reportRepository.findByJob_JobType_Id(jobTypeId)
                .stream().map(ReportResponse::new).collect(Collectors.toList());
    }


    public Optional<Report> getReportById(Integer id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Optional<Report> updateReport(Integer id, Report updatedReport) {
        return reportRepository.findById(id).map(report -> {
            report.setReportName(updatedReport.getReportName());
            report.setCreationDate(updatedReport.getCreationDate());
            report.setContent(updatedReport.getContent());
            report.setAnalysisData(updatedReport.getAnalysisData());
            report.setJob(updatedReport.getJob());
            report.setLand(updatedReport.getLand());
            report.setOwner(updatedReport.getOwner());
            return reportRepository.save(report);
        });
    }

    public boolean deleteReport(Integer id) {
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Report> getReportsByJob(String jobName) {
        return reportRepository.findByJob_JobType_Name(jobName);
    }
}
