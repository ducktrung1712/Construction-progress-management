package com.tland.landsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    float totalArea;

    String priorityLandType;

    @Lob
    byte[] image;

    @Column(columnDefinition = "TEXT")
    String description;

    @OneToMany(mappedBy = "area")
    @JsonIgnore // Ngăn vòng lặp khi serialize JSON
    List<Land> lands;
}

