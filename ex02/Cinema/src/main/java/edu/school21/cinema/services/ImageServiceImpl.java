package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.ImageRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void save(HttpServletRequest request, FileItem fileItem) {
        User user = (User) request.getSession().getAttribute("user");

        imageRepository.save(new Image(user, fileItem));

        Optional<Image> image = imageRepository.findLastByUserID(user.getId());

        if (image.isPresent()) {
            String cloudPath = Utils.getCloudPath(request, image.get());

            Utils.uploadToCloud(cloudPath, fileItem);
        }
    }

    @Override
    public Optional<Image> findLastImage(Long userId) {
        return imageRepository.findLastByUserID(userId);
    }

    @Override
    public Optional<Image> findById(Long userId) {
        return imageRepository.findByID(userId);
    }

    @Override
    public List<Image> findAll(Long userId) {
        return imageRepository.findAllByUserID(userId);
    }
}
