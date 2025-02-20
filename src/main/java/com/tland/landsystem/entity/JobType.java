package com.tland.landsystem.entity;

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
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;  // Sửa 'Id' thành 'id' theo chuẩn Java

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;
}
