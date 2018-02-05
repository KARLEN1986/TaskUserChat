package filter;


import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationFilter implements Filter {

    List<String> excludeUrls = new ArrayList<>();

    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls.add("/");
        excludeUrls.add("/index.jsp");
        excludeUrls.add("/register.jsp");
        excludeUrls.add("/login");
        excludeUrls.add("/register");
        excludeUrls.add(".css");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!excludeUrls.contains(request.getRequestURI())) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect("index.jsp");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
