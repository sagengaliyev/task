package com.sagengaliyev.task.controllers;

import com.sagengaliyev.task.dto.ImageDTO;
import com.sagengaliyev.task.models.Image;
import com.sagengaliyev.task.repositories.ImageRepository;
import com.sagengaliyev.task.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    @ResponseBody
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageService.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    /**
     * Первое задание: загрузчик
     * @param file
     * @param principal
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "image", required = false)  MultipartFile file, Principal principal) throws IOException {
        return new ResponseEntity<>(imageService.upload(file, principal),HttpStatus.OK);
    }


}
