package com.tland.landsystem.dto;

import com.tland.landsystem.entity.Job;
import com.tland.landsystem.entity.Land;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Integer id;
    private String reportName;
    private Date creationDate;
    private String createdByFullName;
    private String content;
    private Job job;
    private Land land;
    private Integer jobTypeId;
    private String jobTypeName;
}
