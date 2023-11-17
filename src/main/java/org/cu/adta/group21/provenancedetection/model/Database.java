package org.cu.adta.group21.provenancedetection.model;

import java.util.List;

public abstract class Database {

    public static List<Products> products;
    public static List<Regions> regions;
    public static List<Routes> routes;
    public static List<Suppliers> suppliers;

    abstract public void loadData();
    
    abstract public Object getColData(String col_name);
    
    abstract public void displayRelation();
    
    public void parseQuery(String query){
        
    }

    public void joinOperation(String relation_one, String relation_two) {
        
    }
    
    
    
   

    

}
