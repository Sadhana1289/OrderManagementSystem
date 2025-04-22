package com.hexaware.util;

import java.sql.*;

public class DBConnUtil {
    public static Connection getDBConn(String propertyFileName) {
        try {
            String url = DBPropertyUtil.getConnectionString(propertyFileName);
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
