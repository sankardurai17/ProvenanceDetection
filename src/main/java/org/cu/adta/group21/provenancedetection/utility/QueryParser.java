package org.cu.adta.group21.provenancedetection.utility;

import java.util.ArrayList;
import java.util.Arrays;
public class QueryParser {
    public static String parseQuery(String query) {
        String table1;
        String table2;
        query=query.toLowerCase();
        String[] qrySplit=query.split("from",2);
        if(qrySplit[0].contains("*")){
            return query;
        }
        else{
            query=query.replace(","," ");
            ArrayList<String> query_list=new ArrayList<>(Arrays.asList(query.split("\\s+")));
            int index_of_join = query_list.indexOf("where");
            table1 = query_list.get(index_of_join - 2);
            table2 = query_list.get(index_of_join - 1);

            String ann1=table1 + ".ann as " + table1 + "_ann,";
            String ann2=table2 + ".ann as " + table2 + "_ann";
            return qrySplit[0]+","+ann1+ann2+" from "+qrySplit[1];
        }
    }
}
