package org.cu.adta.group21.provenancedetection;
import java.util.Map;
import java.util.Scanner;

import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.S;
import org.cu.adta.group21.provenancedetection.model.Suppliers;
import org.cu.adta.group21.provenancedetection.utility.BitMap;
import org.cu.adta.group21.provenancedetection.utility.BitMapJoin;
import org.cu.adta.group21.provenancedetection.utility.QueryParser;
import org.cu.adta.group21.provenancedetection.utility.Util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
            //Query :: "SELECT A2, A3 FROM R, S WHERE R.A2 = S.B2 AND R.A3 = S.B3;"
            //generate compressed bitmaps on Join attributes:: r.a2, s.b2, r.a3, s.b3:

            BitMap ra2x = BitMap.bitMapIndex("a2", "r").compressBitMap();
            BitMap sb2x = BitMap.bitMapIndex("b2","s").compressBitMap();
            BitMap ra3x = BitMap.bitMapIndex("a3", "r").compressBitMap();
            BitMap sb3x = BitMap.bitMapIndex("b3","s").compressBitMap();

            //perform join:
           BitMapJoin.joinUsingBitmap(ra2x,sb2x,ra3x,sb3x);
        }

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
        return ip;
    }
    
    private static void loadRelations(){
        //System.out.println("Enter the Query that you need to perform: ");
        //Scanner in = new Scanner(System.in);
        //String qry = in.nextLine();
        String qry = "select a2, a3 from r, s where r.a2 = s.b2 AND r.a3 = s.b3";
        //long startTimeForParseQuery=System.currentTimeMillis();

        List<String> columnNames=QueryParser.getSelectColumns(qry);
        List<String> tableNames=QueryParser.getJoinRelations(qry);

        R.loadData();
        S.loadData();

//        List<Products> products=Products.loadData();
//        Suppliers.loadData();
//        List<Suppliers> suppliers=Suppliers.suppliers;
//        suppliers.stream().forEach(suppliers1 -> System.out.println(suppliers1.supplier_id));
//        System.out.println(suppliers);
//        Routes.loadData();
//        List<Routes> routes=Routes.routes;
//        routes.stream().forEach(routes1 -> System.out.println(routes1.supplier));
//        System.out.println(routes);
//        //Regions.loadData();
//        //List<Regions> regions=Regions.regions;
//        Map<String,String> joinProvenance=Util.performJoin(suppliers,routes,"supplier_id","supplier",columnNames);
//        displayResult(joinProvenance);
//        System.out.println();
    }

    public static void displayResult(Map<String, String> resultMap) {
        AtomicInteger i= new AtomicInteger(1);
        resultMap.forEach((key, value) -> System.out.println((i.getAndIncrement())+" "+key + " " + value));
    }
}
