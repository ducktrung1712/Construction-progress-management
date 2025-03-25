package com.tland.landsystem.entity;

import com.tland.landsystem.Enum.LandStatus;
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
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    Area area;


    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    Owner owner;

    @Column(nullable = false)
    float areaSize;

    @Column(nullable = false)
    String location;

    @Enumerated(EnumType.STRING)
    LandStatus status;

    @Column(nullable = false)
    String landType;

    @Lob
    byte[] image;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;

    @Column(columnDefinition = "TEXT")
    String description;

}
