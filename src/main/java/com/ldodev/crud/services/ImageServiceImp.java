package com.ldodev.crud.services;

import com.ldodev.crud.repositories.ImageRepository;
import com.ldodev.crud.entities.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImp implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImp(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryService.upload(file);
        String imgUrl = (String) uploadResult.get("url");
        String imgId = (String) uploadResult.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imgUrl, imgId);
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Image image) throws IOException {
        cloudinaryService.delete(image.getImgId());
        imageRepository.deleteById(image.getId());

    }
}
