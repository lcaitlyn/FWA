package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {
    public static String getStringFromPartName(HttpServletRequest request, String partName) {
        try {
            Part part = request.getPart(partName);
            return getStringFormInputStream(part.getInputStream());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getStringFormInputStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }
    public static boolean isValidArgs(String... strings) {
        for (String s : strings) {
            if (s == null) {
                return false;
            }

            if (s.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static Long getImageIdFromPathInfo(String pathInfo) {
        String imageId = pathInfo.replaceFirst("/imageid=", "");

        try {
            return Long.parseLong(imageId);
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public static void generateImages(HttpServletRequest request) {
        String avatar = "/images/default/avatar/avatar.png";

        ImageService imageService = (ImageService) request.getServletContext().getAttribute("imageService");
        User user = (User) request.getSession().getAttribute("user");
        String storagePath = (String) request.getServletContext().getAttribute("storagePath");

        Optional<Image> image = imageService.findLastImage(user.getId());

        if (image.isPresent()) {
            avatar = storagePath + image.get().getFileUniquePath();
        }

        request.setAttribute("avatar", avatar);
        request.setAttribute("images", imageService.findAll(user.getId()));
    }
}
