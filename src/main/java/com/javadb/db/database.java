package com.javadb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {
  // Connection Configurations
  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fintech";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "123456.soft";

  // SQL connection object
  private Connection connection;

  /**
   * Try to make a connection to the database
   * 
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  private Connection connect() throws ClassNotFoundException, SQLException {
    //load the jdbc driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Create a connection to the database.
    connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    return connection;
  }

  /**
   * Query the database
   * 
   * @param sql
   * @return
   * @throws Exception
   */
  public ResultSet getQuery(String sql) {
    ResultSet resultSet = null;
    Connection con = null;
    try {
      con = connect();//make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        resultSet = pStat.executeQuery();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultSet;
  }

  public int postQuery(String sql, Object ...params) {
    int update = -1;
    Connection con = null;
    try {
      con = connect();// make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        int i = 0;
        while (params.length > 0 && i < params.length) {
          pStat.setObject(i + 1, params[i]);
          i ++;
        }
        update = pStat.executeUpdate();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return update;
  }

  public ResultSet getQuery(String sql, Object... params) {
    ResultSet resultSet = null;
    Connection con = null;
    try {
      con = connect();// make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        int i = 0;
        while (params.length > 0 && i < params.length) {
          pStat.setObject(i + 1, params[i]);
          i++;
        }
        resultSet = pStat.executeQuery();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultSet;
  }

  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}