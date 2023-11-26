package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;
import static org.cu.adta.group21.provenancedetection.model.Regions.regions;

/**
 *
 * @author USER
 */
public class Suppliers implements Database {

    public int supplier_id;
    public String supplier_name;
    public String ann;
    public static List<Suppliers> suppliers;

    public Suppliers(int supplier_id, String supplier_name, String ann) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.ann = ann;

    }

    public String getColData(String col_name) {
        if (col_name.compareTo("supplier_id") == 0) {
            return Integer.toString(this.supplier_id);
        } else if (col_name.compareTo("supplier_name") == 0) {
            return this.supplier_name;
        } else if (col_name.compareTo("ann") == 0) {
            return this.ann;
        }

        return null;
    }

    public static void displayRelation() {
        for (int i = 0; i < suppliers.size(); i++) {
            System.out.println(suppliers.get(i).supplier_id + " ");
            System.out.println(suppliers.get(i).supplier_name + " ");
            System.out.println(suppliers.get(i).ann + " ");
            System.out.println("");
        }
    }

    public static void loadData() {
        String query = "select * from suppliers;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Suppliers supplier = new Suppliers(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3));
                suppliers.add(supplier);
            }
        } catch (Exception e) {

        }

    }
}
