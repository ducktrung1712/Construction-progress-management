package com.tland.landsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String reportName;

    @Temporal(TemporalType.DATE)
    Date creationDate;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    Users createdBy;

    @Column(columnDefinition = "TEXT")
    String content;

    @Column(columnDefinition = "TEXT")
    String analysisData;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    Job job;

    @ManyToOne
    @JoinColumn(name = "land_id", referencedColumnName = "id")
    Land land;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    Owner owner;
}
