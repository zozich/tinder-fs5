package ua.danit.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    if (req.getServletPath().equals("/login")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    Cookie[] cookies = req.getCookies();

    boolean userIdFound = false;

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("user-id".equals(cookie.getName())) {
          userIdFound = true;
          break;
        }
      }
    }

    if (userIdFound) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      resp.sendRedirect("/login");
    }
  }

  @Override
  public void destroy() {
  }
}
