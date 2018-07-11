package ua.danit.servlet;

import ua.danit.dao.UsersDao;
import ua.danit.entity.User;
import ua.danit.util.FreemarkerHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
  private UsersDao usersDao;

  public LoginServlet(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();

//    FreemarkerHandler.processTemplate(writer, new HashMap<>(), "login.html");
    writer.write("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <title>Login</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "  <form method=\"POST\" action=\"/login\">\n" +
        "    <label>\n" +
        "      Login\n" +
        "      <input type=\"text\" name=\"login\">\n" +
        "    </label>\n" +
        "    <label>\n" +
        "      Password\n" +
        "      <input type=\"password\" name=\"password\">\n" +
        "    </label>\n" +
        "    <input type=\"submit\">\n" +
        "  </form>\n" +
        "</body>\n" +
        "</html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    User user = usersDao.getUserByLoginAndPassword(login, password);

    if (user == null) {
      resp.getWriter().write("Wrong credentials");
    } else {
      Cookie cookie = new Cookie("user-id", String.valueOf(user.getId()));
      resp.addCookie(cookie);
      resp.sendRedirect("/users");
    }
  }
}
