package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

@MultipartConfig
@WebServlet(name = "ImageServlet", value = "/image/*")
public class ImageServlet extends HttpServlet {
    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        imageService = (ImageService) config.getServletContext().getAttribute("imageService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());

        try {
            for (FileItem fileItem : servletFileUpload.parseRequest(request)) {
                if (!fileItem.getContentType().contains("image/")) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "только изображения!");
                    return;
                }
                imageService.save(request, fileItem);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/profile");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo() == null || !request.getPathInfo().startsWith("/imageid=")
            || Utils.getImageIdFromPathInfo(request.getPathInfo()) == -1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Страница не найдена. Пример: .../image/imageid=3");
            return;
        }

        Long imageId = Utils.getImageIdFromPathInfo(request.getPathInfo());

        Optional<Image> image = imageService.findById(imageId);

        if (!image.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Такого изображения не существует");
            return;
        }

        request.setAttribute("fileUniquePath", image.get().getFileUniquePath());
        request.getRequestDispatcher("/WEB-INF/jsp/image.jsp").forward(request, response);
    }
}
