package com.sagengaliyev.task.dto;

import com.sagengaliyev.task.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
public class ImageDTO {
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] bytes;
    private Long userId;
}
