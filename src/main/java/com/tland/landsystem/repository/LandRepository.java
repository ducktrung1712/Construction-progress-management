package com.tland.landsystem.repository;

import com.tland.landsystem.Entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandRepository extends JpaRepository<Land, Integer> {
    // Bạn có thể định nghĩa thêm các phương thức truy vấn nếu cần
}
