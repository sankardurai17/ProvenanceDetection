package org.cu.adta.group21.provenancedetection.dbconnectivity;

import org.cu.adta.group21.provenancedetection.utility.ProvenanceUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBUtility {
    public static Map<String,String> executeUserQuery(String query){
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet=null;
        Map<String, String> resultMap=new HashMap<String,String>();
        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            //provenance detection
            resultMap=ProvenanceUtil.computeProvenance(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return resultMap;
    }
}
