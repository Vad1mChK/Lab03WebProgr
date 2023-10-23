package org.vad1mchk.webprogr.lab03.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MethodFilter", urlPatterns = {"/*"})
public class MethodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String method = request.getMethod();

        if (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method)) {
            response.setHeader("Allow", "GET, POST");
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
