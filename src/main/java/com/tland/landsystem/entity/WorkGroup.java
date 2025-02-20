package com.tland.landsystem.entity;

import com.tland.landsystem.Enum.WorkGroupStatus;
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
public class WorkGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Temporal(TemporalType.DATE)
    Date deadline;

    String priority;

    @Enumerated(EnumType.STRING)
    WorkGroupStatus status;

    @Lob
    byte[] image;
}
