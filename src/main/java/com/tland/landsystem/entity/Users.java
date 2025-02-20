package com.tland.landsystem.entity;

import com.tland.landsystem.Enum.UserRole;
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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    @ManyToOne
    @JoinColumn(name = "work_group")
    WorkGroup workGroup;
    public void clearPassword() {
        this.password = null;
    }
}
