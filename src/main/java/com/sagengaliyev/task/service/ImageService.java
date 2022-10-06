package com.sagengaliyev.task.service;

import com.sagengaliyev.task.mapper.ImageMapper;
import com.sagengaliyev.task.models.Image;
import com.sagengaliyev.task.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Service
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final UserService userService;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper, UserService userService) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.userService = userService;
    }

    public Optional<Image> findById(Long id){
        return imageRepository.findById(id);
    }

    /**
     * Логика первого задания: загрузчик
     * @param file
     * @param principal
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile file, Principal principal) throws IOException {
        Image image = null;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
        }
        log.info("Saving new image. Title: {};", image.getName());
        imageRepository.save(image);
        String url = "Image has been uploaded successfully! The URL: http://localhost:8080/api/images/" + image.getId();
        return url;
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
