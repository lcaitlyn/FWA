package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.services.ImageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig
@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorized") == null
            || request.getSession().getAttribute("authorized").equals(false)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Сперва нужно авторизоваться! /signIn");
            return;
        }

        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("authorized", false);
            response.sendRedirect(request.getContextPath() + "/signIn");
            return;
        }

        Utils.generateImages(request);

        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }
}
