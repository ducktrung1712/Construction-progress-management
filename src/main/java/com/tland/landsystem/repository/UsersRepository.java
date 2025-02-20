package com.tland.landsystem.repository;

import com.tland.landsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    // Truy vấn người dùng theo username hoặc email
    Optional<Users> findByUsernameOrEmail(String username, String email);

    // Kiểm tra tồn tại theo từng trường riêng biệt
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
