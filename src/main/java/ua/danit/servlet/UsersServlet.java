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

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
  private UsersDao usersDao;

  public UsersServlet(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();

    Map<String, Object> variables = new HashMap<>();
    User user = usersDao.getNotLikedUser();

    if (user == null) {
      resp.sendRedirect("/liked");
      return;
    }

    variables.put("user", user);

    FreemarkerHandler.processTemplate(writer, variables, "users.html");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userChoice = req.getParameter("choice");
    String userId = req.getParameter("userId");

    usersDao.saveLike(userId, "yes".equals(userChoice));

    doGet(req, resp);
  }
}
