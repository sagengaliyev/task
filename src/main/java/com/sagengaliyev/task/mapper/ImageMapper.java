package com.sagengaliyev.task.mapper;

import com.sagengaliyev.task.dto.ImageDTO;
import com.sagengaliyev.task.models.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageDTO toDTO(Image image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setContentType(image.getContentType());
        imageDTO.setName(image.getName());
        imageDTO.setSize(image.getSize());
        imageDTO.setOriginalFileName(image.getOriginalFileName());
        imageDTO.setUserId(image.getUser().getId());
        return imageDTO;
    }

    public Image toEntity(ImageDTO imageDTO){
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setName(imageDTO.getName());
        image.setContentType(imageDTO.getContentType());
        image.setSize(imageDTO.getSize());
        image.setOriginalFileName(imageDTO.getOriginalFileName());
        image.setBytes(imageDTO.getBytes());
        return image;
    }
}
