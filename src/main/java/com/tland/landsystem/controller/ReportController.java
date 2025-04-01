package com.tland.landsystem.controller;

import com.tland.landsystem.dto.ReportDTO;
import com.tland.landsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*") // Allow cross-origin requests from the frontend
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Get all reports (used in ReportManagement.js to fetch all reports)
    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        List<ReportDTO> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // Get reports by user ID
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<ReportDTO>> getReportsByUser(@PathVariable Integer userId) {
        List<ReportDTO> reports = reportService.getReportsByUserId(userId);
        return ResponseEntity.ok(reports);
    }

    // Get single report by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Integer id) {
        Optional<ReportDTO> report = reportService.getReportById(id);
        return report
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create report
    @PostMapping
    public ResponseEntity<?> createReport(
            @RequestParam("reportName") String reportName,
            @RequestParam("content") String content,
            @RequestParam(value = "jobId", required = false) Integer jobId,
            @RequestParam(value = "landId", required = false) Integer landId,
            @RequestParam("createdById") Integer createdById,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "analysisData", required = false) String analysisData) {

        try {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setReportName(reportName);
            reportDTO.setContent(content);
            reportDTO.setJobId(jobId);
            reportDTO.setLandId(landId);
            reportDTO.setCreatedById(createdById);
            reportDTO.setAnalysisData(analysisData);
            reportDTO.setReportDate(new Date());

            if (image != null && !image.isEmpty()) {
                reportDTO.setImageBytes(image.getBytes());
            }

            ReportDTO createdReport = reportService.createReport(reportDTO);
            return ResponseEntity.ok(createdReport);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating report: " + e.getMessage());
        }
    }

    // Update report
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(
            @PathVariable Integer id,
            @RequestParam("reportName") String reportName,
            @RequestParam("content") String content,
            @RequestParam(value = "jobId", required = false) Integer jobId,
            @RequestParam(value = "landId", required = false) Integer landId,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "analysisData", required = false) String analysisData) {

        try {
            // Check if report exists
            if (!reportService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(id);
            reportDTO.setReportName(reportName);
            reportDTO.setContent(content);
            reportDTO.setJobId(jobId);
            reportDTO.setLandId(landId);
            reportDTO.setAnalysisData(analysisData);

            if (image != null && !image.isEmpty()) {
                reportDTO.setImageBytes(image.getBytes());
            }

            ReportDTO updatedReport = reportService.updateReport(reportDTO);
            return ResponseEntity.ok(updatedReport);

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating report: " + e.getMessage());
        }
    }

    // Delete report
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        try {
            if (!reportService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            reportService.deleteReport(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}