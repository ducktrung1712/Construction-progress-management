package com.tland.landsystem.repository;

import com.tland.landsystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    // Find jobs by assigned user ID
    List<Job> findByAssignedToId(Integer userId);

    // Optional JPQL query for more control (eager loading)
    @Query("SELECT j FROM Job j " +
            "LEFT JOIN FETCH j.land " +
            "LEFT JOIN FETCH j.jobType " +
            "LEFT JOIN FETCH j.assignedTo " +
            "WHERE j.assignedTo.id = :userId")
    List<Job> findJobsByAssignedUserIdWithDetails(@Param("userId") Integer userId);
}