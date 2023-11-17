package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

/**
 *
 * @author USER
 */
public class Regions extends Database {
    public int region_id;
    public String region_name;
    public String ann;
    
    public Regions(int region_id, String region_name, String ann){
        this.region_id = region_id;
        this.region_name = region_name;
        this.ann = ann;
                
    }
    
    @Override
    public Object getColData(String col_name){
        if(col_name.compareTo("region_id") == 0){
            return this.region_id;
        }else if(col_name.compareTo("region_name") == 0){
            return this.region_name;
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
        String query = "select * from regions;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Regions region = new Regions(resultSet.getInt(1), 
                resultSet.getString(2), resultSet.getString(3));
                Database.regions.add(region);
            }
        } catch (Exception e) {

        }

    }
}
