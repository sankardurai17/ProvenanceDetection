package org.cu.adta.group21.provenancedetection.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProvenanceUtil {

    public static Map<String, String> computeProvenance(ResultSet rs) {
        Map<String, String> ann_map = new LinkedHashMap<>();
        try {
            int number_of_col = rs.getMetaData().getColumnCount();
            String ann;
            populateColumnNames(rs, number_of_col, ann_map);
            while (rs.next()) {
                /*StringBuilder concatKey = new StringBuilder();
                for (int i = 3; i < number_of_col + 1; i++) {
                    concatKey.append(rs.getObject(i).toString()).append(" ");
                }
                ann = rs.getObject(1).toString()+"."+ rs.getObject(2).toString();
                String key=concatKey.toString().trim();
                if (ann_map.containsKey(key)) {
                    ann_map.put(key, ann_map.get(key.trim()) + "+" + ann);
                } else {
                    ann_map.put(key, ann);
                }*/
                StringBuilder concatKey = new StringBuilder();
                StringBuilder concatValue = new StringBuilder();
                for (int i = 1; i <= number_of_col; i++) {
                    String data = rs.getObject(i).toString();
                    if (rs.getMetaData().getColumnName(i).contains("ann")) {
                        concatValue.append(data).append(".");
                    } else {
                        concatKey.append(data).append(" ");
                    }
                }
                String key = concatKey.toString().trim();
                String value = concatValue.substring(0, concatValue.length() - 1);
                //System.out.println("key "+key);
                //System.out.println("value "+value);
                if (ann_map.containsKey(key)) {
                    ann_map.put(key, ann_map.get(key) + " + " + value);
                } else {
                    ann_map.put(key, value);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ann_map;
    }

    private static void populateColumnNames(ResultSet rs, int number_of_col, Map<String, String> ann_map) throws SQLException {
        /*int k=3;
        StringBuilder columnName=new StringBuilder();
        while(k<=number_of_col){
            columnName.append(rs.getMetaData().getColumnName(k)).append(" ");
            k++;
        }
        String ann_columnName= rs.getMetaData().getColumnLabel(1)+"."+ rs.getMetaData().getColumnLabel(2);

        ann_map.put(columnName.toString().trim(),ann_columnName);*/
        StringBuilder columnName = new StringBuilder();
        StringBuilder annColumnNames = new StringBuilder();
        int k = 1;
        while (k <= number_of_col) {
            if (rs.getMetaData().getColumnName(k).contains("ann")) {
                annColumnNames.append(rs.getMetaData().getTableName(k)).append("_").append(rs.getMetaData().getColumnName(k)).append(" ");
            } else {
                columnName.append(rs.getMetaData().getColumnName(k)).append(" ");
            }
            k++;
        }
        ann_map.put(columnName.toString().trim(), annColumnNames.toString().trim());
    }
}
