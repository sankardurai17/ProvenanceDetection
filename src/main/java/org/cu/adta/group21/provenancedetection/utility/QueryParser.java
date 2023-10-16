package org.cu.adta.group21.provenancedetection.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.UnaryOperator;
import org.cu.adta.group21.provenancedetection.dbconnectivity.MyDBConnection;

public class QueryParser {

    public static String parseQuery(String query) {
        ArrayList<String> query_list = new ArrayList<>(Arrays.asList(query.split(" ")));
        ArrayList<String> query_list_upd = (ArrayList<String>) query_list.clone();
        query_list_upd.replaceAll(s -> s.replace(",", ""));
        String table1 = "";
        String table2 = "";

        ArrayList<String> tables = new ArrayList<String>();
        try {

            ResultSet rsF = new MyDBConnection().getConnection().createStatement().executeQuery("show tables");
            while (rsF.next()) {
                tables.add(rsF.getString(1));
            }

            ArrayList<String> table_list = (ArrayList<String>) query_list_upd.clone();
            table_list.retainAll(tables);
            table1 = table_list.get(0).replace(",", "");
            table2 = table_list.get(1).replace(",", "");

            //inserting ann column in query
            query_list.add(1, table1 + ".ann,");
            query_list.add(2, table2 + ".ann,");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.join(" ", query_list);
    }
}
