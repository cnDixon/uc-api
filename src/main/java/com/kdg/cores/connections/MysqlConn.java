package com.kdg.cores.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {

    public static Connection getPaizhiConn() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://172.21.16.141:3306/ladonnew_db";
        String user = "root";
        String password = "rzvNCHj9s4wVYA";
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
