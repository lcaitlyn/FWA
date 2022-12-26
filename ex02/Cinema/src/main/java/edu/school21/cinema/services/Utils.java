package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class Utils {
    private static final String USERID = "userid=";
    private static final String IMAGEID = "imageid=";
    private static final String NAME = "name=";
    private static final String SLASH = "/";

    public static String getFileUniqueName(Image image) {
        return USERID + image.getUserID() + IMAGEID + image.getID() + NAME + image.getName();
    }

    public static String getFileUniquePath(String storagePath, Image image) {
        return storagePath + SLASH + image.getUserID() + SLASH + getFileUniqueName(image);
    }

    public static String getWebbAppName(String contextPath) {
        return (contextPath.isEmpty()) ? "/ROOT" : contextPath;
    }

    public static String getCloudPath(HttpServletRequest request, Image image) {
        String webappPath = (String) request.getServletContext().getAttribute("webappPath");
        String webappName = getWebbAppName(request.getContextPath());
        String storagePath = (String) request.getServletContext().getAttribute("storagePath");

        return webappPath + webappName + SLASH + getFileUniquePath(storagePath, image);
    }


    public static void uploadToCloud(String cloudPath, FileItem fileItem) {
        File file = new File(cloudPath);

        try {
            fileItem.write(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
