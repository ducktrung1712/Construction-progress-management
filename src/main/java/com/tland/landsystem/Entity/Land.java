package com.tland.landsystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import com.tland.landsystem.Enum.LandStatus;

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
    Integer Id;

    @ManyToOne
    @JoinColumn(name = "area_id")
    Area area;

    @ManyToOne
    @JoinColumn(name = "owner_id")
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
