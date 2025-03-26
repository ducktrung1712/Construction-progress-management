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

    @Lob
    byte[] image;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    Job job;

    @ManyToOne
    @JoinColumn(name = "land_id", referencedColumnName = "id")
    Land land;

}
