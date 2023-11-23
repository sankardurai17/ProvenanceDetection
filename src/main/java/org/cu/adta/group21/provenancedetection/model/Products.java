package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

/**
 *
 * @author USER
 */
public class Products extends Database {

    public int product_id;
    public String product_type;
    public String ann;
    public static List<Products> products;


    public Products(int product_id, String product_type, String ann) {
        this.product_id = product_id;
        this.product_type = product_type;
        this.ann = ann;

    }
    
    @Override
    public String getColData(String col_name){
        if(col_name.compareTo("product_id") == 0){
            return Integer.toString(this.product_id);
        }else if(col_name.compareTo("product_type") == 0){
            return this.product_type;
        }else if(col_name.compareTo("ann") == 0){
            return this.ann;
        }
        
      return null; 
    }
    
    @Override
    public void displayRelation(){
        
    }
    
    public static int getColIndex(String col_name){
        if(col_name.compareTo("product_id") == 0){
            return 0;
        }else if(col_name.compareTo("product_type") == 0){
            return 1;
        }else if(col_name.compareTo("ann") == 0){
            return 2;
        }
        
        return -1;   
    }

    @Override
    public void loadData() {
        String query = "select * from products;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                Products product = new Products(resultSet.getInt(1), 
                resultSet.getString(2), resultSet.getString(3));
                Products.products.add(product);
            }
        } catch (Exception e) {

        }

    }
}
