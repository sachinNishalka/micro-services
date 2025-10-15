package org.example.security;

import jakarta.servlet.*;

import java.io.IOException;


public class MySecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        here if something wants to happen before the filter it should goes here
        System.out.println("Before the filter, here baiscally if yoou want to do something to the request it goes here");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("here if something wants to happen to the response before getting back to the client it goes here");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}