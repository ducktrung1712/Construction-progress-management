package com.tland.landsystem.service;

import com.tland.landsystem.entity.Area;
import com.tland.landsystem.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getAreaById(int id) {
        return areaRepository.findById(id);
    }

    public Area createArea(Area area) {
        return areaRepository.save(area);
    }

    public Area updateArea(int id, Area areaDetails) {
        return areaRepository.findById(id).map(area -> {
            area.setName(areaDetails.getName());
            area.setTotalArea(areaDetails.getTotalArea());
            area.setPriorityLandType(areaDetails.getPriorityLandType());
            area.setImage(areaDetails.getImage());
            area.setDescription(areaDetails.getDescription());
            return areaRepository.save(area);
        }).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khu vực với id: " + id));
    }

    public void deleteArea(int id) {
        if (!areaRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy khu vực với id: " + id);
        }
        areaRepository.deleteById(id);
    }
}
