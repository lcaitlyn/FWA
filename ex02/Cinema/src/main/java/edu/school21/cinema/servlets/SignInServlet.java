package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Log;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.LogsRepository;
import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@MultipartConfig
@WebServlet(name = "SignInServlet", value = "/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;
    private UsersRepository usersRepository;

    private LogsRepository logsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
        logsRepository = (LogsRepository) config.getServletContext().getAttribute("logsRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Utils.getStringFromPartName(request, "email");
        String password = Utils.getStringFromPartName(request, "password");

        if (!Utils.isValidArgs(email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не правильно указаны данные. Пример: Email: andreysidorov228@mail.ru, Password: andrey123");
            return;
        }

        if (!usersService.signIn(email, password)) {
            doGet(request, response);
            return;
        }

        authorization(request);


        response.sendRedirect(request.getContextPath() + "/profile");
    }

    public void authorization(HttpServletRequest request) {
        User user = usersRepository.findByEmail(request.getParameter("email")).get();

        request.getSession().setAttribute("authorized", true);

        request.getSession().setAttribute("user", user);

        logsRepository.save(new Log(user.getEmail(), LocalDateTime.now(), request.getRemoteAddr()));

        request.getSession().setAttribute("logsRepository", logsRepository);
    }
}
