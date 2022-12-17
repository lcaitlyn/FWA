package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
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
@WebServlet(name = "SingUpServlet", value = "/signUp")
public class SingUpServlet extends HttpServlet {
    private ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = getStringFromPartName(request, "firstname");
        String secondName = getStringFromPartName(request, "secondname");
        String email = getStringFromPartName(request, "email");
        String password = getStringFromPartName(request, "password");

        if (isNotValidArgs(firstName, secondName, email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не правильно указаны данные. Пример: Имя: Андрей, Фамилия: Сидоров, Email: andreysidorov228@mail.ru, Password: andrey123");
            return;
        }

        UsersService usersService = (UsersService) applicationContext.getBean("usersService");

        usersService.signUp(firstName, secondName, email, password);

        response.sendRedirect("/signIn");
    }

    private String getStringFromPartName(HttpServletRequest request, String partName) {
        try {
            Part part = request.getPart(partName);
            return getStringFormInputStream(part.getInputStream());
        } catch (Exception e) {
            return null;
        }
    }

    private String getStringFormInputStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }

    private boolean isNotValidArgs(String firstNane, String secondName, String email, String password) {
        return (firstNane == null || secondName == null || email == null || password == null
                || firstNane.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty());
    }
}
