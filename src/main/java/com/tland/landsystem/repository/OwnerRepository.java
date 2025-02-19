package com.tland.landsystem.repository;

import com.tland.landsystem.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    // Bạn có thể thêm các truy vấn tùy chỉnh tại đây nếu cần.
}
