package com.ideas2it.dbconnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * DBConnection class is used to connect our employee application with Mysql
 * Database and close the already opened connection
 */
public class DataBaseConnection {

        private  DataBaseConnection() {}
        private static DataBaseConnection dbconnection = null;       
        private final static String url = "jdbc:mysql://localhost:3306/EmployeeApplication?useSSL=false";
        private final static String user = "root";
        private final static String password = "Jelly22fi$h";
        public static DataBaseConnection getInstance() {
            if(dbconnection == null) {
                dbconnection = new DataBaseConnection();
            } 
            return dbconnection;
        }

        /*
         * GetConnection class is used to connect our employee application with Mysql       
         */
        public Connection getConnection(Connection connection) {
            if(connection == null ) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(url, user, password);
                } catch(ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return connection;
        }
        
        /*
         * CloseConnection class is used to disconnect our employee application with Mysql       
         */
        public void closeConnection(Connection connection) {
            try {    
               connection.close();
            } catch(Exception ex) {
                System.out.println("Connection is not closed" + ex);
            }
        }   
}
