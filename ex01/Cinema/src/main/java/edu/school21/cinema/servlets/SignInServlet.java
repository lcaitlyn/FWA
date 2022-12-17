package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
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
        String email = getStringFromPartName(request, "email");
        String password = getStringFromPartName(request, "password");

        if (isNotValidArgs(email, password)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не правильно указаны данные. Пример: Email: andreysidorov228@mail.ru, Password: andrey123");
            return;
        }

        UsersService usersService = (UsersService) applicationContext.getBean("usersService");

        User user = usersService.signIn(email, password);

        if (user == null) {
            doGet(request, response);
            return;
        }

        response.getWriter().write("<meta charset=\"UTF-8\">");
        response.getWriter().write("Вы успешно авторизовались!<br>");
        response.getWriter().write(user.getFirstName() + "<br>");
        response.getWriter().write(user.getSecondName() + "<br>");
        response.getWriter().write(user.getEmail() + "<br>");
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

    private boolean isNotValidArgs(String email, String password) {
        return (email == null || password == null
                || email.isEmpty() || password.isEmpty());
    }
}
