package org.cu.adta.group21.provenancedetection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.cu.adta.group21.provenancedetection.model.Products;
import org.cu.adta.group21.provenancedetection.model.Regions;
import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.Suppliers;
import org.cu.adta.group21.provenancedetection.utility.BitMap;

/*
 * Main class
 *
 * */
public class Main {

    public static void main(String[] args) {
        int choice = displayWelcomePage();
        loadRelations();
        if(choice == 1){
            //join using sort.
        }
        else{
            //join using bitmap;
            HashMap<String,BitMap> bitMaps =  new HashMap<>();
            bitMaps.put("products.product_id",BitMap.bitMapIndex("product_id","products"));
            bitMaps.put("routes.product",BitMap.bitMapIndex("product","routes"));
            // "SELECT A2, A3 FROM R, S WHERE R.A2 = S.B2 AND R.A3 = S.B3;"
            // "SELECT region_name from routes, products  WHERE routes.product = products.product_id;
            //join(r,s,a2,b2,a3,b3,bitMaps);
        }
        
        Products.displayRelation();
        Routes.displayRelation();
        Regions.displayRelation();
        Suppliers.displayRelation();
        
//        Scanner in = new Scanner(System.in);
//        String qry = in.nextLine();
//        long startTimeForParseQuery=System.currentTimeMillis();
//        String updatedQuery = QueryParser.parseQuery(qry);
//        long endTimeForParseQuery=System.currentTimeMillis();
//        System.out.println("Parse Query method time taken: "+(endTimeForParseQuery-startTimeForParseQuery)+" milli seconds");
//        System.out.println(updatedQuery);
//        long startTimeForExecuteQuery=System.currentTimeMillis();
//        Map<String, String> resultMap = DBUtility.executeUserQuery(updatedQuery);
//        long endTimeForExecuteQuery=System.currentTimeMillis();
//        System.out.println("Execute Query and Provenance Detection method time taken: "+(endTimeForExecuteQuery-startTimeForExecuteQuery)+" milli seconds");
//        displayResult(resultMap);
    }

    public static int displayWelcomePage() {
        System.out.println("Hello and Welcome!! Press 1.Join using sort 2.Join using bitmap");
        Scanner sc = new Scanner(System.in);
        int ip = sc.nextInt();
        System.out.println("Enter the Query that you need to perform: ");
        return ip;
    }

    public static void displayResult(Map<String, String> resultMap) {
        resultMap.forEach((key, value) -> System.out.println(key + " " + value));
    }
    
    private static void loadRelations(){
        Products.loadData();
        Regions.loadData();
        Routes.loadData();
        Suppliers.loadData();
    }
}
