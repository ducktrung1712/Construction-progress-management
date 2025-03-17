package com.tland.landsystem.entity;

import com.tland.landsystem.Enum.TransactionStatus;
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
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String fullName;

    String contact;
    String address;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @Column(columnDefinition = "TEXT")
    String changeHistory;
}
