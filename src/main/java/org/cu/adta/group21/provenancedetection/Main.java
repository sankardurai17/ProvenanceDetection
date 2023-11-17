package org.cu.adta.group21.provenancedetection;

import org.cu.adta.group21.provenancedetection.dbconnectivity.DBUtility;
import org.cu.adta.group21.provenancedetection.utility.QueryParser;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        displayWelcomePage();
        Scanner in = new Scanner(System.in);
        String qry = in.nextLine();
        long startTimeForParseQuery = System.currentTimeMillis();
        String updatedQuery = QueryParser.parseQuery(qry);
        long endTimeForParseQuery = System.currentTimeMillis();
        System.out.println("Parse Query method time taken: " + (endTimeForParseQuery - startTimeForParseQuery) + " milli seconds");
        System.out.println(updatedQuery);
        long startTimeForExecuteQuery = System.currentTimeMillis();
        Map<String, String> resultMap = DBUtility.executeUserQuery(updatedQuery);
        long endTimeForExecuteQuery = System.currentTimeMillis();
        System.out.println("Execute Query and Provenance Detection method time taken: " + (endTimeForExecuteQuery - startTimeForExecuteQuery) + " milli seconds");
        displayResult(resultMap);
    }

    public static void displayWelcomePage() {
        System.out.println("Hello and Welcome!!" + "Enter the Query that you need to perform: ");
    }

    public static void displayResult(Map<String, String> resultMap) {
        resultMap.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
