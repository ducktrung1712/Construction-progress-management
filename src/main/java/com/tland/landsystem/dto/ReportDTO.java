package com.tland.landsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Integer id;
    private String reportName;
    private Date reportDate;
    private Integer createdById;
    private String createdByFullName;
    private String content;
    private String image; // Base64 encoded for response
    private byte[] imageBytes; // For request only
    private String analysisData;
    private Integer landId;
    private String landLocation;
    private Integer jobId;
    private String jobTypeName;
}