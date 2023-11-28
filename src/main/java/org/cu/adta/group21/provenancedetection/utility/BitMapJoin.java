package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.Main;
import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.S;

import java.util.*;

public class BitMapJoin {

    //for where conditions:
    public static ArrayList<Boolean> andOperation(ArrayList<Boolean> arr1, ArrayList<Boolean> arr2){
        int size = arr1.size()>arr2.size() ? arr2.size() : arr1.size();
        ArrayList<Boolean> res = new ArrayList<>();
        for(int i=0;i<size;i++){
            res.add(arr1.get(i) && arr2.get(i));
        }
        return res;
    }

    public static ArrayList<Boolean> OrOperation(ArrayList<Boolean> arr1, ArrayList<Boolean> arr2){
        int size = arr1.size()>arr2.size() ? arr2.size() : arr1.size();
        ArrayList<Boolean> res = new ArrayList<>();
        for(int i=0;i<size;i++){
            res.add(arr1.get(i) || arr2.get(i));
        }
        if(arr1.size()>size){
            int i=size;
            while(i<arr1.size()){
                res.add(arr1.get(i));
                i++;
            }
        }
        if(arr2.size()>size){
            int i=size;
            while(i<arr2.size()){
                res.add(arr2.get(i));
                i++;
            }
        }
        return res;
    }

    //join on 2 attributes with AND condition: R.A2 = S.B2 AND R.A3 = S.B3;
    public static void joinUsingBitmap(BitMap ra2, BitMap sb2, BitMap ra3, BitMap sb3){

        Map<String, String> ann_map = new LinkedHashMap<>();

        //decompression of bitmaps for join attributes.
        ra2.decompressBitMap();
        sb2.decompressBitMap();
        ra3.decompressBitMap();
        sb3.decompressBitMap();

        //using bitmap index to check first join attribute:
        for(String attr : ra2.attributeValMap.keySet()){
            if(sb2.attributeValMap.containsKey(attr)) {
                 ArrayList<Boolean> rtuples = ra2.bitVector.get(ra2.attributeValMap.get(attr));
                 ArrayList<Boolean> stuples = sb2.bitVector.get(sb2.attributeValMap.get(attr));

                 for(int i=0;i<rtuples.size();i++){
                     if(rtuples.get(i).equals(Boolean.TRUE)){
                         for(int j=0;j<stuples.size();j++){
                             if(stuples.get(j).equals(Boolean.TRUE)){
                                 //check second condition of join.
                                 if(i>R.r_relation.size() || j>S.s_relation.size()){
                                     System.out.println("Error!!");
                                     continue;
                                 }
                                 R tuplei = R.r_relation.get(i);
                                 S tuplej = S.s_relation.get(j);
                                 if(tuplei.a3 == tuplej.b3){
                                     String res = Integer.toString(tuplei.a2) + " " + Integer.toString(tuplei.a3);
                                     String annval = tuplei.ann.toString() + "." + tuplej.ann.toString();
                                     if(ann_map.containsKey(res)) {
                                         annval = ann_map.get(res) + " + " + annval;
                                     }
                                     ann_map.put(res, annval);
                                 }
                             }
                         }
                     }
                 }
            }
        }

        Main.displayResult(ann_map);
    }

}
