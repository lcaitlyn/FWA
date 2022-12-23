package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UsersRepository;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "SingUpServlet", value = "/signUp")
public class SingUpServlet extends HttpServlet {
    private UsersService usersService;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = Utils.getStringFromPartName(request, "firstname");
        String secondName = Utils.getStringFromPartName(request, "secondname");
        String email = Utils.getStringFromPartName(request, "email");
        String password = Utils.getStringFromPartName(request, "password");

        if (!Utils.isValidArgs(firstName, secondName, email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не правильно указаны данные. Пример: Имя: Андрей, Фамилия: Сидоров, Email: andreysidorov228@mail.ru, Password: andrey123");
            return;
        }

        if (usersRepository.findByEmail(email).isPresent()) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "Пользователь с таким e-mail уже существует!");
            return;
        }

        usersService.signUp(firstName, secondName, email, password);

        response.sendRedirect(request.getContextPath() + "/signIn");
    }
}
