package org.cu.adta.group21.provenancedetection.utility;

import java.util.ArrayList;
import java.util.Arrays;
public class QueryParser {
    public static String parseQuery(String query) {
        String table1;
        String table2;

        ArrayList<String> query_list = new ArrayList<>(Arrays.asList(query.split(" ")));
        int index_of_join = query_list.indexOf("where");
        table1 = query_list.get(index_of_join - 2).replace(",", "");
        table2 = query_list.get(index_of_join - 1);

        //inserting ann column in query
        query_list.add(1, table1 + ".ann as " + table1 + "_ann,");
        query_list.add(2, table2 + ".ann as " + table2 + "_ann,");

        return String.join(" ", query_list);
    }
}
