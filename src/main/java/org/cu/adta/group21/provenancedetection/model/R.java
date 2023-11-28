package org.cu.adta.group21.provenancedetection.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

public class R implements Database{
    public int a1;
    public int a2;
    public int a3;
    public int a4;
    public String ann;
    public int i;
    public static List<R> r_relation=new ArrayList<R>();
    
    public R(int a1, int a2, int a3, int a4, String ann, int i){
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.ann = ann;
        this.i = i;
    }
    
    @Override
    public String getColData(String col_name) {
        if (col_name.compareTo("a1") == 0) {
            return Integer.toString(this.a1);
        } else if (col_name.compareTo("a2") == 0) {
            return Integer.toString(this.a2);
        } else if (col_name.compareTo("a3") == 0) {
            return Integer.toString(this.a3);
        }else if (col_name.compareTo("a4") == 0) {
            return Integer.toString(this.a4);
        }else if (col_name.compareTo("ann") == 0) {
            return this.ann;
        }else if (col_name.compareTo("i") == 0) {
            return Integer.toString(this.i);
        }

        return null;
    }

    public String getRowData(){
        return Integer.toString(this.a1) + Integer.toString(this.a2) + Integer.toString(this.a3) + Integer.toString(this.a4) + Integer.toString(this.i);
    }

    public static void loadData() {
        String query = "select * from r;";
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet = null;

        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                R r = new R(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getInt(6));
                r_relation.add(r);
            }
        } catch (Exception e) {

        }

    }
}
