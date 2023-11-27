package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;
import static org.cu.adta.group21.provenancedetection.model.Products.products;

/**
 *
 * @author USER
 */
public class Regions implements Database {

    public int region_id;
    public String region_name;
    public String ann;
    public static List<Regions> regions=new ArrayList<>();

    public Regions(int region_id, String region_name, String ann) {
        this.region_id = region_id;
        this.region_name = region_name;
        this.ann = ann;

    }

    @Override
    public String getColData(String col_name) {
        if (col_name.compareTo("region_id") == 0) {
            return Integer.toString(this.region_id);
        } else if (col_name.compareTo("region_name") == 0) {
            return this.region_name;
        } else if (col_name.compareTo("ann") == 0) {
            return this.ann;
        }

        return null;
    }

    public static void displayRelation() {
        for (int i = 0; i < regions.size(); i++) {
            System.out.print(regions.get(i).region_id + " ");
            System.out.print(regions.get(i).region_name + " ");
            System.out.print(regions.get(i).ann + " ");
            System.out.println();
        }
    }

    public static void loadData() {
        String query = "select * from regions;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Regions region = new Regions(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3));
                regions.add(region);
            }
        } catch (Exception e) {

        }

    }
}
