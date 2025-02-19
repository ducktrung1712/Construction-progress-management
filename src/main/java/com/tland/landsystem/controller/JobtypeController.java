package com.tland.landsystem.controller;

import com.tland.landsystem.Entity.JobType;
import com.tland.landsystem.Entity.Land;
import com.tland.landsystem.repository.JobTypeRepository;
import com.tland.landsystem.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobtypes")
public class JobtypeController {

    private final JobTypeRepository jobtypeRepository;

    @Autowired
    public JobtypeController(JobTypeRepository jobtypeRepository) {
        this.jobtypeRepository = jobtypeRepository;
    }

    // Lấy danh sách tất cả jobtype
    @GetMapping
    public List<JobType> getAllJobtypes() {
        return jobtypeRepository.findAll();
    }

    // Lấy chi tiết jobtype theo id
    @GetMapping("/{id}")
    public ResponseEntity<JobType> getJobtypeById(@PathVariable Integer id) {
        Optional<JobType> jobtypeOptional = jobtypeRepository.findById(id);
        return jobtypeOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới jobtype
    @PostMapping
    public ResponseEntity<JobType> createJobtype(@RequestBody JobType jobtype) {
        JobType createdJobtype = jobtypeRepository.save(jobtype);
        return ResponseEntity.ok(createdJobtype);
    }

    // Cập nhật jobtype
    @PutMapping("/{id}")
    public ResponseEntity<JobType> updateJobtype(@PathVariable Integer id, @RequestBody JobType jobtypeDetails) {
        Optional<JobType> jobtypeOptional = jobtypeRepository.findById(id);
        if (!jobtypeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        JobType jobtype = jobtypeOptional.get();
        jobtype.setName(jobtypeDetails.getName());
        jobtype.setDescription(jobtypeDetails.getDescription());
        JobType updatedJobtype = jobtypeRepository.save(jobtype);
        return ResponseEntity.ok(updatedJobtype);
    }

    // Xóa jobtype
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobtype(@PathVariable Integer id) {
        if (!jobtypeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobtypeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RestController
    @RequestMapping("/api/lands")
    public static class LandController {

        @Autowired
        private LandRepository landRepository;

        // Lấy danh sách tất cả các đất
        @GetMapping
        public List<Land> getAllLands() {
            return landRepository.findAll();
        }

        // Lấy thông tin một đất theo id
        @GetMapping("/{id}")
        public ResponseEntity<Land> getLandById(@PathVariable int id) {
            Optional<Land> land = landRepository.findById(id);
            return land.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Tạo mới một đất
        @PostMapping
        public Land createLand(@RequestBody Land land) {
            return landRepository.save(land);
        }

        // Cập nhật thông tin một đất theo id
        @PutMapping("/{id}")
        public ResponseEntity<Land> updateLand(@PathVariable int id, @RequestBody Land landDetails) {
            Optional<Land> optionalLand = landRepository.findById(id);
            if (optionalLand.isPresent()) {
                Land land = optionalLand.get();

                // Sử dụng setArea và setOwner thay vì setAreaId và setOwnerId
                land.setArea(landDetails.getArea());
                land.setOwner(landDetails.getOwner());

                land.setLocation(landDetails.getLocation());
                land.setStatus(landDetails.getStatus());
                land.setLandType(landDetails.getLandType());
                land.setImage(landDetails.getImage());
                land.setDescription(landDetails.getDescription());

                Land updatedLand = landRepository.save(land);
                return ResponseEntity.ok(updatedLand);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        // Xóa một đất theo id
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteLand(@PathVariable int id) {
            if (landRepository.existsById(id)) {
                landRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
