package com.tland.landsystem.dto;


import com.tland.landsystem.entity.Job;
import com.tland.landsystem.entity.Land;
import com.tland.landsystem.entity.Report;
import com.tland.landsystem.entity.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
    Integer id;
    String reportName;
    Date creationDate;
    Users createdBy;
    String content;
    String analysisData;
    Job job;
    Land land;
    Integer jobTypeId;  // Thêm jobTypeId
    String jobTypeName; // Thêm jobTypeName

    public ReportResponse(Integer id, String reportName, Date creationDate, Users createdBy, String content,
                          String analysisData, Job job, Land land, Integer jobTypeId, String jobTypeName) {
        this.id = id;
        this.reportName = reportName;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.content = content;
        this.analysisData = analysisData;
        this.job = job;
        this.land = land;
        this.jobTypeId = jobTypeId;
        this.jobTypeName = jobTypeName;
    }

    public ReportResponse(Report report) {
    }
}