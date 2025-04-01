package com.tland.landsystem.repository;

import com.tland.landsystem.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    // Find reports by creator user ID
    List<Report> findByCreatedById(Integer userId);

    // Alternative with JPQL query for more control (e.g., eager loading relationships)
    @Query("SELECT r FROM Report r JOIN FETCH r.createdBy WHERE r.createdBy.id = :userId")
    List<Report> findReportsByUserIdWithCreator(@Param("userId") Integer userId);
}