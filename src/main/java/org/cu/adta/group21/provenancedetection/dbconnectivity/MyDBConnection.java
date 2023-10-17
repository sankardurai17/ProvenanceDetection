package org.cu.adta.group21.provenancedetection.dbconnectivity;

import org.cu.adta.group21.provenancedetection.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
* Database Connection Class
* */
public class MyDBConnection {
    private static Connection con = null;
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(Constants.CONNECTION_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(con!=null){
                System.out.println("Connection Established");
            }
        }
        return con;
    }
    public void close(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                System.out.println("Connection Closed");
            }
        }
    }
}
