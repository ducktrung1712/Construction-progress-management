package com.tland.landsystem.repository;

import com.tland.landsystem.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByJob_JobType_Name(String name);
    @Query("SELECT r FROM Report r WHERE r.job.jobType.id = :jobTypeId")
    List<Report> findByJob_JobType_Id(@Param("jobTypeId") Integer jobTypeId);


}
