package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.Main;
import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.S;

import java.util.*;

public class BitMapJoin {

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

    //join on 2 attributes with AND condition: R.A2 = S.B2 AND R.A3 = S.B3;"
    public static void joinUsingBitmap(BitMap ra2, BitMap sb2, BitMap ra3, BitMap sb3){

        //decompression of bitmaps for join attributes.
//        ra2.decompressBitMap();
//        sb2.decompressBitMap();
//        ra3.decompressBitMap();
//        sb3.decompressBitMap();


        System.out.println(ra2);
        System.out.println(sb2);
        System.out.println(ra3);
        System.out.println(sb3);
        int tableRsize = ra2.bitVector.get(0).size();
        int tableSsize = sb2.bitVector.get(0).size();
        ArrayList<Boolean> tuplesR = new ArrayList<>(tableRsize);
        tuplesR.addAll(Collections.nCopies(tableRsize, Boolean.FALSE));
        ArrayList<Boolean> tuplesS = new ArrayList<>(tableSsize);
        tuplesS.addAll(Collections.nCopies(tableRsize, Boolean.FALSE));

        //storing resulting tuples from R and S for first condition:
        for(String attr : ra2.attributeValMap.keySet()){
            if(sb2.attributeValMap.containsKey(attr)){
                 int rowIndexR = ra2.attributeValMap.get(attr);
                 int rowIndexS = sb2.attributeValMap.get(attr);
                 tuplesR = BitMapJoin.OrOperation(tuplesR ,ra2.bitVector.get(rowIndexR));
                 tuplesS = BitMapJoin.OrOperation(tuplesS ,sb2.bitVector.get(rowIndexS));
            }
        }

        //storing resulting tuples from R and S for 2nd condition:
        ArrayList<Boolean> tuplesR2 = new ArrayList<>(tableRsize);
        tuplesR.addAll(Collections.nCopies(tableRsize, Boolean.FALSE));
        ArrayList<Boolean> tuplesS2 = new ArrayList<>(tableSsize);
        tuplesS.addAll(Collections.nCopies(tableRsize, Boolean.FALSE));

        for(String attr : ra3.attributeValMap.keySet()){
            if(sb3.attributeValMap.containsKey(attr)){
                int rowIndexR = ra3.attributeValMap.get(attr);
                int rowIndexS = sb3.attributeValMap.get(attr);
                tuplesR2 = BitMapJoin.OrOperation(tuplesR2 ,ra3.bitVector.get(rowIndexR));
                tuplesS2 = BitMapJoin.OrOperation(tuplesS2 ,sb3.bitVector.get(rowIndexS));
            }
        }

        //AND condition in where clause:
        tuplesR = andOperation(tuplesR, tuplesR2);
        tuplesS = andOperation(tuplesS, tuplesS2);
        calculateProvenance(tuplesR, tuplesS);
    }

    //SELECT A2, A3
    public static void calculateProvenance(ArrayList<Boolean> tuplesR, ArrayList<Boolean> tuplesS) {
        Map<String, String> ann_map = new LinkedHashMap<>();
        StringBuilder tupleValues = new StringBuilder();
        StringBuilder annValues = new StringBuilder();

        for(int i=0;i<tuplesR.size();i++){
            if(tuplesR.get(i) == true) {
                for (int j = 0; j < tuplesS.size(); j++) {
                    if (tuplesS.get(j) == true){
                        //get tuples i and j to be joined.
                        R tuplei = R.r_relation.get(i);
                        S tuplej = S.s_relation.get(j);
                        //String res = tuplei.getRowData() + tuplej.getRowData();
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
        Main.displayResult(ann_map);
    }
}
