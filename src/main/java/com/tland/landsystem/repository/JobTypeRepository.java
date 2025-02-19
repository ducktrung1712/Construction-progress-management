package com.tland.landsystem.repository;

import com.tland.landsystem.Entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
}
