package com.java.CarConnect.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnUtil {

	public static Connection GetConnection(String connStr) throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String driver = rb.getString("driver");
		String user = rb.getString("user");
		String pwd = rb.getString("password");
		Class.forName(driver);
		Connection con = DriverManager.getConnection(connStr, user, pwd);
		return con;
	}
}

//package com.java.CarConnect.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//import com.java.CarConnect.exception.DatabaseConnectionException;
//
//public class DBConnUtil {
//
//    public static Connection getConnection(String connStr) throws DatabaseConnectionException {
//        try {
//            ResourceBundle rb = ResourceBundle.getBundle("db");
//            String driver = rb.getString("driver");
//            String user = rb.getString("user");
//            String pwd = rb.getString("password");
//            Class.forName(driver);
//            Connection con = DriverManager.getConnection(connStr, user, pwd);
//            return con;
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new DatabaseConnectionException("Unable to establish a connection to the database.");
//        }
//    }
//}



