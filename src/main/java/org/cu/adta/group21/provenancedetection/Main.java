package org.cu.adta.group21.provenancedetection;

import org.cu.adta.group21.provenancedetection.model.Routes;
import org.cu.adta.group21.provenancedetection.model.Suppliers;
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
        displayWelcomePage();
        Scanner in = new Scanner(System.in);
        String qry = in.nextLine();
        //long startTimeForParseQuery=System.currentTimeMillis();

        List<String> columnNames=QueryParser.getSelectColumns(qry);
        List<String> tableNames=QueryParser.getJoinRelations(qry);

        //List<Products> products=Products.loadData();
        Suppliers.loadData();
        List<Suppliers> suppliers=Suppliers.suppliers;
        suppliers.stream().forEach(suppliers1 -> System.out.println(suppliers1.supplier_id));
        System.out.println(suppliers);
        Routes.loadData();
        List<Routes> routes=Routes.routes;
        routes.stream().forEach(routes1 -> System.out.println(routes1.supplier));
        System.out.println(routes);
        //Regions.loadData();
        //List<Regions> regions=Regions.regions;
        Map<String,String> joinProvenance=Util.performJoin(suppliers,routes,"supplier_id","supplier",columnNames);
        displayResult(joinProvenance);
        System.out.println();


        /*String tableName1="org.cu.adta.group21.provenancedetection.model."+tableNames.get(0).substring(0,1).toUpperCase()+tableNames.get(0).substring(1).toLowerCase();
        String tableName2="org.cu.adta.group21.provenancedetection.model."+tableNames.get(1).substring(0,1).toUpperCase()+tableNames.get(1).substring(1).toLowerCase();
       */
        /*try {
            Object instance1=instantiateClass(tableName1);
            if(instance1 instanceof Suppliers){
                System.out.println("Suppliers");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/








        //if(tableNames.get(0).equals("Suppliers")||)
        //GenericListHandler<?,?> obj= new GenericListHandler<?,?>(list1,list2,columnNames.get(0),columnNames.get(1));
        //if(tableNames.get(0).equals())



       /* String updatedQuery = QueryParser.parseQuery(qry);
        String queries[]=updatedQuery.split(",");
        System.out.println(updatedQuery);
        System.exit(0);
        long endTimeForParseQuery=System.currentTimeMillis();
        System.out.println("Parse Query method time taken: "+(endTimeForParseQuery-startTimeForParseQuery)+" milli seconds");
        System.out.println(updatedQuery);
        long startTimeForExecuteQuery=System.currentTimeMillis();
        Map<String, String> resultMap = DBUtility.executeUserQuery(updatedQuery);
        long endTimeForExecuteQuery=System.currentTimeMillis();
        System.out.println("Execute Query and Provenance Detection method time taken: "+(endTimeForExecuteQuery-startTimeForExecuteQuery)+" milli seconds");
        displayResult(resultMap);*/
    }


    public static void displayWelcomePage() {
        System.out.println("Hello and Welcome!!" + "Enter the Query that you need to perform: ");
    }

    public static void displayResult(Map<String, String> resultMap) {
        AtomicInteger i= new AtomicInteger(1);
        resultMap.forEach((key, value) -> System.out.println((i.getAndIncrement())+" "+key + " " + value));
    }
}
