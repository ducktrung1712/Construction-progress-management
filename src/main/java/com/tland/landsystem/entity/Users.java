package com.tland.landsystem.entity;

import com.tland.landsystem.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


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

    @Column(nullable = true)
    String hometown;

    @Column(length = 15, nullable = true)
    @Pattern(regexp = "^(\\+84|0)[0-9]{9,10}$", message = "Số điện thoại không hợp lệ")
    String phone;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    @Enumerated(EnumType.STRING)
    UserRole role;

    @ManyToOne
    @JoinColumn(name = "work_group", referencedColumnName = "id")
    WorkGroup workGroup;

    public void clearPassword() {
        this.password = null;
    }
}