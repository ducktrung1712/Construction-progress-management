package com.tland.landsystem.service;

import com.tland.landsystem.entity.Land;
import com.tland.landsystem.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandService {
    private final LandRepository landRepository;

    @Autowired
    public LandService(LandRepository landRepository) {
        this.landRepository = landRepository;
    }

    // Lấy danh sách tất cả các đất
    public List<Land> getAllLands() {
        return landRepository.findAll();
    }

    // Lấy thông tin một đất theo id
    public Optional<Land> getLandById(int id) {
        return landRepository.findById(id);
    }

    // Tạo mới một đất
    public Land createLand(Land land) {
        return landRepository.save(land);
    }

    // Cập nhật thông tin một đất theo id
    public Optional<Land> updateLand(int id, Land landDetails) {
        return landRepository.findById(id).map(land -> {
            land.setArea(landDetails.getArea());
            land.setOwner(landDetails.getOwner());
            land.setLocation(landDetails.getLocation());
            land.setStatus(landDetails.getStatus());
            land.setLandType(landDetails.getLandType());
            land.setImage(landDetails.getImage());
            land.setDescription(landDetails.getDescription());
            return landRepository.save(land);
        });
    }

    // Xóa một đất theo id
    public boolean deleteLand(int id) {
        if (landRepository.existsById(id)) {
            landRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

