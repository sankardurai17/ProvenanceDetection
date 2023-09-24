package org.cu.adta.group21.provenancedetection;

import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
* Main class
* */
public class Main {
    public static void main(String[] args) {
        //String query=args[0];
        MyDBConnection connection = new MyDBConnection();
        //Just created a connection between db and code and queried join operation and printed the results
        try {
            Statement st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from products,routes where product=products.product_id and routes.region_from=5");
            System.out.println(rs.getMetaData().getColumnName(1));
            System.out.println(rs.getMetaData().getColumnCount());
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                int index = 1;
                while (index != columnCount + 1) {
                    System.out.print(rs.getObject(index) + " ");
                    index++;
                }
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}