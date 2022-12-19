package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/signIn", "/signUp"})
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("authorized") == null) {
            req.getSession().setAttribute("authorized", false);
        }

        if (req.getSession().getAttribute("authorized").equals(true)) {
            resp.sendRedirect("/profile");
            return;
        }

        chain.doFilter(request, response);
    }
}
