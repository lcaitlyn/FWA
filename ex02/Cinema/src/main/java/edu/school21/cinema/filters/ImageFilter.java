package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ImageFilter", value = "/image/*")
public class ImageFilter implements Filter {
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

        if (req.getSession().getAttribute("authorized").equals(false)) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
            return;
        }

        chain.doFilter(request, response);
    }
}
