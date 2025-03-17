package com.tland.landsystem.controller;

import com.tland.landsystem.dto.ReportResponse;
import com.tland.landsystem.entity.Report;
import com.tland.landsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Lấy danh sách báo cáo (tất cả hoặc lọc theo jobTypeId)
    @GetMapping
    public ResponseEntity<List<ReportResponse>> getReports(@RequestParam(required = false) Integer jobTypeId) {
        if (jobTypeId != null) {
            return ResponseEntity.ok(reportService.getReportsByJobType(jobTypeId));
        }
        return ResponseEntity.ok(reportService.getAllReports());
    }

    // Lấy báo cáo theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới báo cáo
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.createReport(report));
    }

    // Cập nhật báo cáo
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @RequestBody Report updatedReport) {
        return reportService.updateReport(id, updatedReport)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Xóa báo cáo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        return reportService.deleteReport(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Lấy danh sách báo cáo theo tên công việc
    @GetMapping("/by-job/{jobName}")
    public ResponseEntity<List<Report>> getReportsByJob(@PathVariable String jobName) {
        return ResponseEntity.ok(reportService.getReportsByJob(jobName));
    }
}
