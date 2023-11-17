package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

/**
 *
 * @author USER
 */
public class Suppliers extends Database{
    public int supplier_id;
    public String supplier_name;
    public String ann;
    
    public Suppliers(int supplier_id, String supplier_name, String ann){
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.ann = ann;
        
    }
    
    public Object getColData(String col_name){
        if(col_name.compareTo("supplier_id") == 0){
            return this.supplier_id;
        }else if(col_name.compareTo("supplier_name") == 0){
            return this.supplier_name;
        }else if(col_name.compareTo("ann") == 0){
            return this.ann;
        }
        
        return -1;   
    }
    
    @Override
    public void displayRelation(){
        
    }
    
    @Override
    public void loadData() {
        String query = "select * from suppliers;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Suppliers supplier = new Suppliers(resultSet.getInt(1), 
                resultSet.getString(2), resultSet.getString(3));
                Database.suppliers.add(supplier);
            }
        } catch (Exception e) {

        }

    }
}
