package org.cu.adta.group21.provenancedetection;

<<<<<<< Updated upstream
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
=======
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
* Main class
* */
>>>>>>> Stashed changes
public class Main {

    public static void main(String[] args) {
<<<<<<< Updated upstream
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
=======
        //String query=args[0];
        MyDBConnection connection = new MyDBConnection();
        //Just created a connection between db and code and queried join operation and printed the results

        //SELECT <specific column names> FROM <relation1>, <relation2> WHERE <conditions>
        Scanner sc = new Scanner(System.in);

        ArrayList<String> col_select_list = new ArrayList<String>();

        System.out.println("SQL Query: ");
        // String query = sc.nextLine();

        //Select A2,A3 from R,S where R.A2=S.B2 AND R.A3=S.B3;
        try {
            Statement st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select product_type, region_to from products join routes where products.product_id = routes.product;");
            String query = "select product_type, region_to from products join routes where products.product_id = routes.product;";
            //creating a query that asks for annotation columns

            //getting the table names from query
            String table1 = "";
            String table2 = "";

            ArrayList<String> query_list = new ArrayList<>(Arrays.asList(query.split(" ")));
            int index_of_join = query_list.indexOf("join");
            table1 = query_list.get(index_of_join - 1);
            table2 = query_list.get(index_of_join + 1);

            //inserting ann column in query
            query_list.add(1, table1 + ".ann,");
            query_list.add(2, table2 + ".ann,");

            String query_arr[] = new String[query_list.size()];
            for (int i = 0; i < query_list.size(); i++) {
                query_arr[i] = query_list.get(i);
            }

            String ann_query = String.join(" ", query_arr);

            HashMap<String, String> ann_map = new HashMap<String, String>();

            rs = st.executeQuery(ann_query);
           
            int number_of_col = rs.getMetaData().getColumnCount();
            // select products.ann, routes.ann, product_type, region_to from products join routes where products.product_id = routes.product;
            String ann;
            while(rs.next()){
                String key = "";
                for(int i=3; i<number_of_col + 1; i++){
                    key = key + rs.getObject(i).toString();
                }
                ann = rs.getObject(1).toString() + rs.getObject(2).toString();
                if(ann_map.containsKey(key)){
                    ann_map.put(key, ann_map.get(key) + "+" + ann);
                }else{
                    ann_map.put(key, ann);
                }
            }
            
            System.out.println(ann_map);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
>>>>>>> Stashed changes
        }
    }
}
