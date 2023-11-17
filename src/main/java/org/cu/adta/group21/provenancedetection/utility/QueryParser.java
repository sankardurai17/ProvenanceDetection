package org.cu.adta.group21.provenancedetection.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParser {

    public static List<String> getJoinRelations(String query) {
        List<String> tables = new ArrayList<String>();
        query = query.toLowerCase();

        query = query.replace(",", " ");
        ArrayList<String> query_list = new ArrayList<>(Arrays.asList(query.split("\\s+")));
        int index_of_join = query_list.indexOf("where");
        tables.add(query_list.get(index_of_join - 2));
        tables.add(query_list.get(index_of_join - 1));
        return tables;

    }

    public static List<String> getSelectColumns(String query) {
        List<String> columns = new ArrayList<String>();
        query = query.toLowerCase();
        query = query.replace(",", " ");
        ArrayList<String> query_list = new ArrayList<>(Arrays.asList(query.split("\\s+")));
        int index_of_select = query_list.indexOf("select");
        int index_of_from = query_list.indexOf("from");
        for (int i = index_of_select + 1; i < index_of_from - 1; i++) {
            columns.add(query_list.get(i));
        }

        return columns;

    }

    public static String getWhereCondition(String query) {
        query = query.toLowerCase();
        query = query.replace(",", " ");

        return query.split("where", 2)[1];
    }

    public static String getOperator(String equation) {
        if (equation.contains("=")) {
            return "=";
        } else if (equation.contains(">=")) {
            return ">=";
        } else if (equation.contains("<=")) {
            return "<=";
        } else if (equation.contains(">")) {
            return ">";
        } else if (equation.contains("<")) {
            return "<";
        } else if (equation.contains("!=")) {
            return "!=";
        }
        return "";
    }

    public static String parseQuery(String query) {
        return "";
    }
}
