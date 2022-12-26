package edu.school21.cinema.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
