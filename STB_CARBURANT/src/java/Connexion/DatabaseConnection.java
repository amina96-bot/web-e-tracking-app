/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aloubchir
 */
public class DatabaseConnection {
    
 private final String Server="192.168.0.106";
 private final String database="NAFTAL";
 private final String user="sa";
 private final String password="_Admin@SBG$";
 
 private final String Server2="192.168.0.59";
 private final String database2="hrStarbrands";
 
 private static Connection connection;
 
  public static Connection getConnection() {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection=DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
    }catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    } 
    return connection;
}
  
  public static Connection getConnection2() {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection=DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer2()+";database="+new DatabaseConnection().getDatabase2()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
    }catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    } 
    return connection;
}

public DatabaseConnection() {
}

public String getServer() {
    return Server;
}

public String getDatabase() {
    return database;
}

public String getUser() {
    return user;
}

public String getPassword() {
    return password;
}

public String getServer2() {
    return Server2;
}

public String getDatabase2() {
    return database2;
}
}
