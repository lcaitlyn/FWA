package edu.school21.cinema.servlets;

import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignInServlet", value = "/signIn")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext applicationContext = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        UsersService usersService = (UsersService) applicationContext.getBean("usersService");
        usersService.signIn(null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
