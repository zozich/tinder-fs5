package ua.danit.servlet;

import ua.danit.dao.UsersDao;
import ua.danit.entity.User;
import ua.danit.util.FreemarkerHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/liked")
public class LikedServlet extends HttpServlet {
  private UsersDao usersDao;

  public LikedServlet(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    Map<String, Object> variables = new HashMap<>();
    variables.put("users", usersDao.getLikedUsers());

    FreemarkerHandler.processTemplate(writer, variables, "liked.html");
  }
}