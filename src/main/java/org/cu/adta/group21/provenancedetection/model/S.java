package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;
import static org.cu.adta.group21.provenancedetection.model.Routes.routes;

/**
 *
 * @author USER
 */
public class S implements Database{

    public int b1;
    public int b2;
    public int b3;
    public int b4;
    public String ann;
    public int i;
    public static List<S> s_relation=new ArrayList<S>();
    
    public S(int b1, int b2, int b3, int b4, String ann, int i){
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.ann = ann;
        this.i = i;
    }
    
    @Override
    public String getColData(String col_name) {
        if (col_name.compareTo("b1") == 0) {
            return Integer.toString(this.b1);
        } else if (col_name.compareTo("b2") == 0) {
            return Integer.toString(this.b2);
        } else if (col_name.compareTo("b3") == 0) {
            return Integer.toString(this.b3);
        }else if (col_name.compareTo("b4") == 0) {
            return Integer.toString(this.b4);
        }else if (col_name.compareTo("ann") == 0) {
            return this.ann;
        }else if (col_name.compareTo("i") == 0) {
            return Integer.toString(this.i);
        }

        return null;
    }
    
    public static void loadData() {
        String query = "select * from s;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                S s = new S(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getInt(6));
                s_relation.add(s);
            }
        } catch (Exception e) {

        }

    }
    
}
