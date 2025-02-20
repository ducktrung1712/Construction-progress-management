package com.tland.landsystem.service;

import com.tland.landsystem.dto.ResourceNotFoundException;
import com.tland.landsystem.entity.Owner;
import com.tland.landsystem.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // Lấy danh sách tất cả các owner
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Lấy thông tin một owner theo id
    public Owner getOwnerById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy owner với id " + id));
    }

    // Tạo mới một owner
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    // Cập nhật thông tin của một owner
    public Owner updateOwner(Integer id, Owner ownerDetails) {
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
    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy owner với id " + id));
        ownerRepository.delete(owner);
    }
}
