package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UsersService;
import edu.school21.cinema.utils.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet(name = "SignInServlet", value = "/signIn")
public class SignInServlet extends HttpServlet {
    private ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = Utils.getStringFromPartName(request, "email");
        String password = Utils.getStringFromPartName(request, "password");

        if (Utils.isValidArgs(email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не правильно указаны данные. Пример: Email: andreysidorov228@mail.ru, Password: andrey123");
            return;
        }

        UsersService usersService = (UsersService) applicationContext.getBean("usersService");

        if (usersService.signIn(email, password)) {

        }

        doGet(request, response);
    }

    private void
}
