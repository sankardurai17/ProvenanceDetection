package org.cu.adta.group21.provenancedetection.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProvenanceUtil {
    public static Map<String,String> computeProvenance(ResultSet rs){
        Map<String, String> ann_map = new HashMap<>();
        try {
            int number_of_col = rs.getMetaData().getColumnCount();
            String ann;
            while (rs.next()) {
                StringBuilder concatKey = new StringBuilder();
                for (int i = 3; i < number_of_col + 1; i++) {
                    concatKey.append(rs.getObject(i).toString()).append(" ");
                }
                ann = rs.getObject(1).toString()+"."+ rs.getObject(2).toString();
                String key=concatKey.toString().trim();
                if (ann_map.containsKey(key)) {
                    ann_map.put(key, ann_map.get(key.trim()) + "+" + ann);
                } else {
                    ann_map.put(key, ann);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return ann_map;
    }
}