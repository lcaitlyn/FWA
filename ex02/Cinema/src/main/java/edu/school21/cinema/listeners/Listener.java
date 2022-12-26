package edu.school21.cinema.listeners;

import edu.school21.cinema.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("webappPath", System.getProperty("catalina.home") + "/webapps");
        servletContext.setAttribute("storagePath", applicationContext.getBean("storagePath"));
        servletContext.setAttribute("applicationContext", applicationContext);
        servletContext.setAttribute("usersService", applicationContext.getBean("usersService"));
        servletContext.setAttribute("usersRepository", applicationContext.getBean("usersRepository"));
        servletContext.setAttribute("logsRepository", applicationContext.getBean("logsRepository"));
        servletContext.setAttribute("imageService", applicationContext.getBean("imageService"));
    }
}
