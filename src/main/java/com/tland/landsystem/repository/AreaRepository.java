package com.tland.landsystem.repository;

import com.tland.landsystem.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
    // Bạn có thể định nghĩa thêm các phương thức tìm kiếm nếu cần
}
