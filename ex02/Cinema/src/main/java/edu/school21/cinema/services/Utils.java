package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class Utils {
    public static String getWebbAppName(String contextPath) {
        return (contextPath.isEmpty()) ? "/ROOT" : contextPath;
    }

    public static String getCloudPath(HttpServletRequest request, Image image) {
        String webappPath = (String) request.getServletContext().getAttribute("webappPath");
        String webappName = getWebbAppName(request.getContextPath());
        String storagePath = (String) request.getServletContext().getAttribute("storagePath");

        return webappPath + webappName + storagePath + image.getFileUniquePath();
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
