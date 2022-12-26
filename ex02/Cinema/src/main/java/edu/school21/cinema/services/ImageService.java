package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    void save(HttpServletRequest request, FileItem fileItem);
    Optional<Image> findLastImage(Long userId);
    Optional<Image> findById(Long userId);

    List<Image> findAll(Long userId);
}
