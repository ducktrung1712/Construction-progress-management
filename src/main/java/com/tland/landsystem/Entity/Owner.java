package com.tland.landsystem.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.tland.landsystem.Enum.TransactionStatus;

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
    Integer Id;

    @Column(nullable = false)
    String fullName;

    String contact;
    String address;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @Column(columnDefinition = "TEXT")
    String changeHistory;
}
