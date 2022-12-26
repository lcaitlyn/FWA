package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.ImageRepository;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final String storagePath;
    private String catalinaHome;
    private String contextPath;

    @Deprecated
    public ImageServiceImpl(ImageRepository imageRepository, String storagePath) {
        this.imageRepository = imageRepository;
        this.storagePath = storagePath;
    }

    public void setPaths(String catalinaHome, String contextPath) {
        this.catalinaHome = catalinaHome;
        this.contextPath = contextPath;
    }

    @Override
    public void save(HttpServletRequest request, FileItem fileItem) {
        User user = (User) request.getSession().getAttribute("user");

        imageRepository.save(new Image(user, fileItem));

        Optional<Image> image = imageRepository.findLastByUserID(user.getId());

        if (image.isPresent()) {
            String cloudPath = Utils.getCloudPath(request, image.get());

            Utils.uploadToCloud(cloudPath, fileItem);

            System.out.println(cloudPath);
        }
    }

    @Override
    public Optional<File> findLastImage() {
        return Optional.empty();
    }

    @Override
    public Optional<File> findById(Long id) {
        Optional<Image> image = imageRepository.findByID(id);

//        if (image.isPresent()) {
//            String cloudPath = Utils.getCloudPath(storagePath, image.get());
//            return Optional.of(new File(cloudPath));
//        }

        return Optional.empty();
    }

    @Override
    public List<File> findAll() {
        return null;
    }
}
