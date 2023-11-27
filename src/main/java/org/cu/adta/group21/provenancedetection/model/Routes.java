package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;
import static org.cu.adta.group21.provenancedetection.model.Regions.regions;

/**
 *
 * @author USER
 */
public class Routes implements Database {

    public int route_id;
    public int region_from;
    public int region_to;
    public int supplier;
    public int product;
    public String ann;
    public static List<Routes> routes = new ArrayList<>();

    public Routes(int route_id, int region_from, int region_to, int supplier, int product, String ann) {
        this.route_id = route_id;
        this.region_from = region_from;
        this.region_to = region_to;
        this.supplier = supplier;
        this.product = product;
        this.ann = ann;
    }

    @Override
    public String getColData(String col_name) {
        if (col_name.compareTo("route_id") == 0) {
            return Integer.toString(this.route_id);
        } else if (col_name.compareTo("region_from") == 0) {
            return Integer.toString(this.region_from);
        } else if (col_name.compareTo("region_to") == 0) {
            return Integer.toString(this.region_to);
        } else if (col_name.compareTo("supplier") == 0) {
            return Integer.toString(this.supplier);
        } else if (col_name.compareTo("product") == 0) {
            return Integer.toString(this.product);
        } else if (col_name.compareTo("ann") == 0) {
            return this.ann;
        }

        return null;
    }

    public static void displayRelation() {
        for (int i = 0; i < routes.size(); i++) {
            System.out.print(routes.get(i).route_id + " ");
            System.out.print(routes.get(i).region_from + " ");
            System.out.print(routes.get(i).region_to + " ");
            System.out.print(routes.get(i).supplier + " ");
            System.out.print(routes.get(i).product + " ");
            System.out.print(routes.get(i).ann + " ");
            System.out.println();
        }
    }

    public static void loadData() {
        String query = "select * from routes;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Routes route = new Routes(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5),
                        resultSet.getString(6));
                routes.add(route);
            }
        } catch (Exception e) {

        }

    }
}
