package com.tland.landsystem.service;

import com.tland.landsystem.entity.Land;
import com.tland.landsystem.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandService {

    @Autowired
    private LandRepository landRepository;

    public List<Land> getAllLands() {
        return landRepository.findAll();
    }

    public Land getLandById(Integer id) {
        return landRepository.findById(id).orElseThrow(() -> new RuntimeException("Land not found"));
    }

    public Land createLand(Land land) {
        return landRepository.save(land);
    }

    public Land updateLand(Integer id, Land landDetails) {
        Land land = getLandById(id);
        land.setArea(landDetails.getArea());
        land.setOwner(landDetails.getOwner());
        land.setAreaSize(landDetails.getAreaSize());
        land.setLocation(landDetails.getLocation());
        land.setStatus(landDetails.getStatus());
        land.setLandType(landDetails.getLandType());
        land.setImage(landDetails.getImage());
        land.setDescription(landDetails.getDescription());
        return landRepository.save(land);
    }

    public void deleteLand(Integer id) {
        landRepository.deleteById(id);
    }
}