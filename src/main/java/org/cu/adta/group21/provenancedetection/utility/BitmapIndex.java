package org.cu.adta.group21.provenancedetection.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.cu.adta.group21.provenancedetection.model.Products;
import org.cu.adta.group21.provenancedetection.model.Regions;
import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.Suppliers;


public class BitmapIndex {
     public static String runLengthDecoding(String bin){
        List<Integer> runs = new ArrayList<Integer>();
        String decompress_bit="";
        int one_length = 0;
        int i=0;
        while(i < bin.length()){
            if (Character.compare(bin.charAt(i), '1') == 0) {
            one_length++;
            i++;
            } else {
                i++;
                one_length++;
                String run_str = "";
                for(int j=0; j<one_length; j++){
                    Character ch = bin.charAt(i++);
                    run_str = run_str + ch.toString();
                }
                one_length = 0;
                
                runs.add(Integer.parseInt(run_str, 2));
            }
            
        }
        
        for(i =0; i<runs.size(); i++){
            for(int j=0; j< runs.get(i); j++){
                decompress_bit = decompress_bit + "0";
            }
            decompress_bit = decompress_bit + "1";
        }
        return decompress_bit;
    }

    public static String runLengthEncoding(String bin) {
        int i = 0;
        List<Integer> runs = new ArrayList<Integer>();
        int zero_length = 0;
        while (i < bin.length()) {  
            if (Character.compare(bin.charAt(i), '0') == 0) {
                zero_length++;
            } else {
                runs.add(zero_length);
                zero_length = 0;
            }

            i++;

        }

        String compress_bin = "";
        for (i = 0; i < runs.size(); i++) {
            int log_value = (int)Math.round(Math.log(runs.get(i)) / Math.log(2));
            for (int j = 0; j < log_value - 1; j++) {
                compress_bin = compress_bin + "1";
            }
            
            compress_bin = compress_bin + "0" + Integer.toBinaryString(runs.get(i));
            
        }
        
        return compress_bin;
    }

    public HashMap<String, String> bitMapIndex(String col_name, String table_name) {
        HashMap<String, String> bitmap = new HashMap<String, String>();
        List<String> values = new ArrayList<String>();
        if (table_name.compareTo("products") == 0) {
            for (int i = 0; i < Products.products.size(); i++) {
                values.add(Products.products.get(i).getColData(col_name));
            }

        } else if (table_name.compareTo("regions") == 0) {

            for (int i = 0; i < Regions.regions.size(); i++) {
                values.add(Regions.regions.get(i).getColData(col_name));
            }
        } else if (table_name.compareTo("routes") == 0) {
            for (int i = 0; i < Routes.routes.size(); i++) {
                values.add(Routes.routes.get(i).getColData(col_name));
            }
        } else if (table_name.compareTo("suppliers") == 0) {
            for (int i = 0; Suppliers.suppliers.size() >= i; i++) {
                values.add(Suppliers.suppliers.get(i).getColData(col_name));
            }
        } 

        List<String> distinct_values = values.stream().distinct().collect(Collectors.toList());

        for (int i = 0; i < distinct_values.size(); i++) {
            String bit = "";
            for (int j = 0; j < values.size(); j++) {
                if (values.get(j).compareTo(distinct_values.get(i)) == 0) {
                    bit = bit + "1";
                } else {
                    bit = bit + "0";
                }
            }

            bitmap.put(distinct_values.get(i), bit);
        }

        return bitmap;

    }
}
