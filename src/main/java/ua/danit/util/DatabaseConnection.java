package ua.danit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  public static Connection getConnection() throws SQLException {
    String URL = "jdbc:mysql://danit.cukm9c6zpjo8.us-west-2.rds.amazonaws.com:3306/fs5";
    String USER = "fs5_user";
    String PASSWORD = "bArceloNa";

    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
