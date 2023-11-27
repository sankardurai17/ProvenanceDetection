package org.cu.adta.group21.provenancedetection.utility;

import java.util.ArrayList;
import java.util.HashMap;

public class BitMapJoin {

    public static ArrayList<Boolean> andOperation(ArrayList<Boolean> arr1, ArrayList<Boolean> arr2){
        ArrayList<Boolean> res = new ArrayList<>();
        for(int i=0;i<arr1.size();i++){
            res.add(arr1.get(i) && arr2.get(i));
        }
        return res;
    }

    public static ArrayList<Boolean> OrOperation(ArrayList<Boolean> arr1, ArrayList<Boolean> arr2){
        ArrayList<Boolean> res = new ArrayList<>();
        for(int i=0;i<arr1.size();i++){
            res.add(arr1.get(i) || arr2.get(i));
        }
        return res;
    }

    //join on 2 attributes.
    public static void joinUsingBitmap(String relA, String relB, String col1a, String col1b, String col2a, String col2b, HashMap<String,BitMap> bitmaps){
        //"SELECT A2, A3 FROM R, S WHERE R.A2 = S.B2 AND R.A3 = S.B3;"
        //join(r,s,a2,b2,a3,b3,bitMaps);

        BitMap b1 = bitmaps.get("products.product_id");
        BitMap b2 = bitmaps.get("routes.product");


        ArrayList<Boolean> resultsRelation1 = new ArrayList<>();
        ArrayList<Boolean> resultsRelation2 = new ArrayList<>();
        for(String attr : b1.attributeValMap.keySet()){
            if(b2.attributeValMap.containsKey(attr)){
                ArrayList<Boolean> resultJoin = BitMapJoin.andOperation(b1.bitVector.get(b1.attributeValMap.get(attr)), b2.bitVector.get(b2.attributeValMap.get(attr)));
                finalResult = OrOperation(finalResult,resultJoin);
            }
        }



    }
}
