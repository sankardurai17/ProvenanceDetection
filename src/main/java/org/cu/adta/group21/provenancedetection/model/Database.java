package org.cu.adta.group21.provenancedetection.model;

public interface Database {

    abstract public void loadData();
    
    abstract public String getColData(String col_name);
    
    abstract public void displayRelation();
    
    //public void parseQuery(String query;

   // public void joinOperation(String relation_one, String relation_two, String colu_one, String col_two);

}
