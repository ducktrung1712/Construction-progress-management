package com.tland.landsystem.dto;

import com.tland.landsystem.entity.Job;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {
    Integer id;
    Integer landId;
    Integer jobTypeId;
    Integer assignedTo;
    String description;
    String jobTypeName;
    String landLocation;
    String status;

    public JobDTO(Job job) {
        this.id = job.getId();
        this.description = job.getDescription();
        this.status = String.valueOf(job.getStatus());
        this.landId = (job.getLand() != null) ? job.getLand().getId() : null;
        this.jobTypeId = (job.getJobType() != null) ? job.getJobType().getId() : null;
        this.assignedTo = (job.getAssignedTo() != null) ? job.getAssignedTo().getId() : null;
        this.jobTypeName = (job.getJobType() != null) ? job.getJobType().getName() : null;
        this.landLocation = (job.getLand() != null) ? job.getLand().getLocation() : null;
    }
}