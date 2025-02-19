package com.tland.landsystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.tland.landsystem.Enum.JobStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @ManyToOne
    @JoinColumn(name = "land_id")
    Land land;

    @ManyToOne
    @JoinColumn(name = "job_type_id")
    JobType jobType;

    @Enumerated(EnumType.STRING)
    JobStatus status;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    Users assignedTo;

    @Column(columnDefinition = "TEXT")
    String description;

    @Lob
    byte[] image;
}
