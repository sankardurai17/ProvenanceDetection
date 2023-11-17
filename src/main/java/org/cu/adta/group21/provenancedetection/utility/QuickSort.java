package org.cu.adta.group21.provenancedetection.utility;
import java.util.Collections;
import java.util.List;
import org.cu.adta.group21.provenancedetection.model.Database;
import static org.cu.adta.group21.provenancedetection.model.Database.products;
import static org.cu.adta.group21.provenancedetection.model.Database.regions;
import static org.cu.adta.group21.provenancedetection.model.Database.routes;
import static org.cu.adta.group21.provenancedetection.model.Database.suppliers;
import org.cu.adta.group21.provenancedetection.model.Products;
import org.cu.adta.group21.provenancedetection.model.Regions;
import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.Suppliers;

public class QuickSort {

    public static int partition(List<?> arr, String col_name, int start, int end) {
        String pivot = "";
        if (arr.get(end) instanceof Products) {
            pivot = (String) ((Products) arr.get(end)).getColData(col_name);
            
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if (((String)Database.products.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Database.products, i, j);
                }
            }
            Collections.swap(Database.products, i+1, end);
         
            return (i + 1);

        } else if (arr.get(end) instanceof Regions) {
            pivot = (String) ((Regions) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if (((String)Database.regions.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Database.regions, i, j);
                }
            }
            
            Collections.swap(Database.regions, i+1, end);
         
            return (i + 1);
        } else if (arr.get(end) instanceof Routes) {
            pivot = (String) ((Routes) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if (((String)Database.routes.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Database.routes, i, j);
                }
            }
            
            Collections.swap(Database.routes, i+1, end);
         
            return (i + 1);
        } else if (arr.get(end) instanceof Suppliers) {
            pivot = (String) ((Suppliers) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if (((String)Database.suppliers.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Database.suppliers, i, j);
                }
            }
            
            Collections.swap(Database.suppliers, i+1, end);
         
            return (i + 1);
        }
        
        return -1;

    }

    public static void quick(List<?> arr, String col_name, int start, int end) {

        if (start < end) {
            int p = partition(arr, col_name, start, end);
            quick(arr, col_name, start, p - 1);
            quick(arr, col_name, p + 1, end);
        }
    }
    
    public static void sort(String col_name, String table_name) {
        if (table_name.compareTo("products") == 0) {
            QuickSort.quick(Database.products, col_name, 0, products.size() - 1);
        } else if (table_name.compareTo("regions") == 0) {
            QuickSort.quick(Database.regions, col_name, 0, regions.size() - 1);
        } else if (table_name.compareTo("routes") == 0) {
            QuickSort.quick(Database.routes, col_name, 0, routes.size() - 1);
        } else if (table_name.compareTo("suppliers") == 0) {
            QuickSort.quick(Database.suppliers, col_name, 0, suppliers.size() - 1);
        }
    }
}
