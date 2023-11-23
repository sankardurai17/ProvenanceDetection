package org.cu.adta.group21.provenancedetection.utility;
import java.util.Collections;
import java.util.List;
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
                if ((Products.products.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Products.products, i, j);
                }
            }
            Collections.swap(Products.products, i+1, end);
         
            return (i + 1);

        } else if (arr.get(end) instanceof Regions) {
            pivot = ((Regions) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if ((Regions.regions.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Regions.regions, i, j);
                }
            }
            
            Collections.swap(Regions.regions, i+1, end);
         
            return (i + 1);
        } else if (arr.get(end) instanceof Routes) {
            pivot = (String) ((Routes) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if ((Routes.routes.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Routes.routes, i, j);
                }
            }
            
            Collections.swap(Routes.routes, i+1, end);
         
            return (i + 1);
        } else if (arr.get(end) instanceof Suppliers) {
            pivot = (String) ((Suppliers) arr.get(end)).getColData(col_name);
 
            int i = (start - 1);
            for (int j = start; j <= end - 1; j++) {
                if ((Suppliers.suppliers.get(j).getColData(col_name)).compareTo(pivot) < 0) {
                    i++;
                    Collections.swap(Suppliers.suppliers, i, j);
                }
            }
            
            Collections.swap(Suppliers.suppliers, i+1, end);
         
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
            QuickSort.quick(Products.products, col_name, 0, Products.products.size() - 1);
        } else if (table_name.compareTo("regions") == 0) {
            QuickSort.quick(Regions.regions, col_name, 0, Regions.regions.size() - 1);
        } else if (table_name.compareTo("routes") == 0) {
            QuickSort.quick(Routes.routes, col_name, 0, Routes.routes.size() - 1);
        } else if (table_name.compareTo("suppliers") == 0) {
            QuickSort.quick(Suppliers.suppliers, col_name, 0, Suppliers.suppliers.size() - 1);
        }
    }
}
