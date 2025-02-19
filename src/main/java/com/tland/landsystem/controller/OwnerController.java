package com.tland.landsystem.controller;

import com.tland.landsystem.dto.ResourceNotFoundException;
import com.tland.landsystem.Entity.Owner;
import com.tland.landsystem.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    // Lấy danh sách tất cả các owner
    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Lấy thông tin một owner theo id
    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy owner với id " + id));
    }

    // Tạo mới một owner
    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerRepository.save(owner);
    }

    // Cập nhật thông tin của một owner
    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable Integer id, @RequestBody Owner ownerDetails) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy owner với id " + id));

        owner.setFullName(ownerDetails.getFullName());
        owner.setContact(ownerDetails.getContact());
        owner.setAddress(ownerDetails.getAddress());
        owner.setTransactionStatus(ownerDetails.getTransactionStatus());
        owner.setChangeHistory(ownerDetails.getChangeHistory());

        return ownerRepository.save(owner);
    }

    // Xóa một owner
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy owner với id " + id));
        ownerRepository.delete(owner);
    }
}
