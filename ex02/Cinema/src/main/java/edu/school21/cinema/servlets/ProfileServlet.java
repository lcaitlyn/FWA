package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Log;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.LogsRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private ApplicationContext applicationContext;
    private LogsRepository logsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        logsRepository = (LogsRepository) applicationContext.getBean("logsRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("authorized") == null
            || request.getSession().getAttribute("authorized").equals(false)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Сперва нужно авторизоваться! /signIn");
            return;
        }

        request.setAttribute("logsRepository", logsRepository);
        User user = (User) request.getSession().getAttribute("user");



        PrintWriter printWriter = response.getWriter();

        for (Log log : logsRepository.findAll(user.getEmail())) {
            printWriter.println(log.getDate().toLocalDate());
            printWriter.println(log.getDate().toLocalTime());
            printWriter.println(log.getIp());
        }


        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }
}
