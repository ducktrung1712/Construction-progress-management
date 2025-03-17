package com.tland.landsystem.dto;

import com.tland.landsystem.Enum.WorkGroupStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkGroupRequest {
    String name;
    String description;
    String deadline;
    String priority;
    WorkGroupStatus status;
    String imageBase64; // Chứa ảnh dưới dạng Base64

    // Getters và Setters
}
