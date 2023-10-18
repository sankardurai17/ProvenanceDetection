package org.cu.adta.group21.provenancedetection.dbconnectivity;

import org.cu.adta.group21.provenancedetection.utility.ProvenanceUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBUtility {
    public static Map<String,String> executeUserQuery(String query){
        MyDBConnection connection = new MyDBConnection();
        ResultSet resultSet;
        Map<String, String> resultMap=new HashMap<>();
        try {
            Statement st = connection.getConnection().createStatement();
            resultSet = st.executeQuery(query);
            //provenance detection
            long startTimeForProvenanceComputation=System.currentTimeMillis();
            resultMap=ProvenanceUtil.computeProvenance(resultSet);
            long endTimeForProvenanceComputation=System.currentTimeMillis();
            System.out.println("Provenance Computation method time taken : "+(endTimeForProvenanceComputation-startTimeForProvenanceComputation)+" in time in milli seconds");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return resultMap;
    }
}
