package ua.danit.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.dao.UsersDao;
import ua.danit.filter.LoginFilter;
import ua.danit.servlet.LikedServlet;
import ua.danit.servlet.LoginServlet;
import ua.danit.servlet.MessagesServlet;
import ua.danit.servlet.UsersServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderServer {
  public void start(String port) throws Exception {
    Server server = new Server(Integer.parseInt(port));
    ServletContextHandler handler = new ServletContextHandler();

    UsersDao usersDao = new UsersDao();

    ServletHolder usersHolder = new ServletHolder(new UsersServlet(usersDao));
    handler.addServlet(usersHolder, "/users");

    ServletHolder likedHolder = new ServletHolder(new LikedServlet(usersDao));
    handler.addServlet(likedHolder, "/liked");

    ServletHolder messagesHolder = new ServletHolder(new MessagesServlet());
    handler.addServlet(messagesHolder, "/messages/*");

    ServletHolder loginHolder = new ServletHolder(new LoginServlet(usersDao));
    handler.addServlet(loginHolder, "/login");

    FilterHolder loginFilter = new FilterHolder(new LoginFilter());
    handler.addFilter(loginFilter, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));

    server.setHandler(handler);
    server.start();
    server.join();
  }
}
