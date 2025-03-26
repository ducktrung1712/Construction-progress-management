package com.tland.landsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tland.landsystem.Enum.JobStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Tránh lỗi JSON serialization
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Giảm N+1 Query
    @JoinColumn(name = "land_id")
    Land land;

    @JsonIgnore  // ✅ Bỏ qua jobType khi serialize JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_type_id")
    JobType jobType;

    @Enumerated(EnumType.STRING) // ✅ Lưu Enum dưới dạng chuỗi "Paused", "In progress", "Completed"
    JobStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    Users assignedTo;

    @Column(columnDefinition = "TEXT")
    String description;

}
