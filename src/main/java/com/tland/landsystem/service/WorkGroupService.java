package com.tland.landsystem.service;

import com.tland.landsystem.entity.WorkGroup;
import com.tland.landsystem.repository.WorkGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkGroupService {

    @Autowired
    private WorkGroupRepository workGroupRepository;

    // Lấy danh sách tất cả WorkGroup
    public List<WorkGroup> getAllWorkGroups() {
        return workGroupRepository.findAll();
    }

    // Lấy WorkGroup theo ID
    public WorkGroup getWorkGroupById(Integer id) {
        return workGroupRepository.findById(id).orElse(null);
    }

    // Tạo WorkGroup mới
    public WorkGroup createWorkGroup(WorkGroup workGroup) {
        return workGroupRepository.save(workGroup);
    }

    // Cập nhật WorkGroup
    public WorkGroup updateWorkGroup(Integer id, WorkGroup updatedWorkGroup) {
        Optional<WorkGroup> optionalWorkGroup = workGroupRepository.findById(id);
        if (!optionalWorkGroup.isPresent()) {
            return null;
        }

        WorkGroup existingWorkGroup = optionalWorkGroup.get();
        existingWorkGroup.setName(updatedWorkGroup.getName());
        existingWorkGroup.setDescription(updatedWorkGroup.getDescription());
        existingWorkGroup.setDeadline(updatedWorkGroup.getDeadline());
        existingWorkGroup.setPriority(updatedWorkGroup.getPriority());
        existingWorkGroup.setStatus(updatedWorkGroup.getStatus());

        if (updatedWorkGroup.getImage() != null) {
            existingWorkGroup.setImage(updatedWorkGroup.getImage());
        }

        return workGroupRepository.save(existingWorkGroup);
    }

    // Xóa WorkGroup
    public boolean deleteWorkGroup(Integer id) {
        if (!workGroupRepository.existsById(id)) {
            return false;
        }
        workGroupRepository.deleteById(id);
        return true;
    }
}
