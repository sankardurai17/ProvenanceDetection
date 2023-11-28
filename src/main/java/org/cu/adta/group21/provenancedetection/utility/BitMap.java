package org.cu.adta.group21.provenancedetection.utility;
import org.cu.adta.group21.provenancedetection.model.Products;
import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.S;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BitMap {

    /**
     * maps the unique attribute value to the index in bitmap array for that value.
     */
    HashMap<String, Integer> attributeValMap;
    /**
     * bitvector of the relation-table.
     */
    ArrayList<ArrayList<Boolean>> bitVector;

    BitMap(){
        attributeValMap = new HashMap<>();
        bitVector = new ArrayList<>();
    }

    public static ArrayList<Boolean> runLengthEncoding(ArrayList<Boolean> str) {
        List<Integer> runs = new ArrayList<Integer>();
        int zero_length = 0;
        for(Boolean val:str){
            if(val==false){
                zero_length++;
            }
            else{
                runs.add(zero_length);
                zero_length = 0;
            }
        }
        ArrayList<Boolean> compressed = new ArrayList<>();
        for (int i = 0; i < runs.size(); i++) {
            int bit_count = Integer.toBinaryString(runs.get(i)).length();
            //int bit_count = (int) (1 + Math.floor(Math.log(runs.get(i)) / Math.log(2)));
            for (int j = 0; j < bit_count - 1; j++) {
                compressed.add(true);
            }
            compressed.add(false);
            String bin = Integer.toBinaryString(runs.get(i));
            for(int j=0;j<bin.length();j++){
                if(bin.charAt(j) == '0') compressed.add(false);
                else if(bin.charAt(j) == '1')compressed.add(true);
                else System.out.println("errr");
            }
        }
        return compressed;
    }

    public static ArrayList<Boolean> runLengthDecoding(ArrayList<Boolean> bin) {
        ArrayList<Boolean> res = new ArrayList<>();
        int i=0,one_length=0;
        while(i<bin.size()){
            while(i<bin.size() && bin.get(i) == true){
                i++; one_length++;
            }
            if(i<bin.size()){
                i++;
                String str= "";
                for(int j=0;j<=one_length && i<bin.size();j++){
                    if(bin.get(i) == false){
                        str = str + "0";
                    }
                    else{
                        str = str + "1";
                    }
                    i++;
                }
                int cntZeroes = Integer.parseInt(str, 2);
                for (int k = 0; k < cntZeroes; k++) {
                    res.add(false);
                }
                res.add(true);
                one_length =0;
            }
        }
        return res;
    }

    public static BitMap bitMapIndex(String col_name, String table_name) {
        BitMap bitMap = new BitMap();
        if (table_name.equalsIgnoreCase("S")) {
            int tableSize = S.s_relation.size();
            for (int i = 0; i < tableSize; i++) {
                String data = S.s_relation.get(i).getColData(col_name);
                if(bitMap.attributeValMap.containsKey(data)){
                    int rowIndex = bitMap.attributeValMap.get(data);
                    bitMap.bitVector.get(rowIndex).set(i,true);
                }
                else{
                    ArrayList<Boolean> newMap = new ArrayList<Boolean>(tableSize);
                    newMap.addAll(Collections.nCopies(tableSize, Boolean.FALSE));
                    int rows = bitMap.bitVector.size();
                    bitMap.attributeValMap.put(data,rows);
                    bitMap.bitVector.add(newMap);
                    bitMap.bitVector.get(rows).set(i,true);
                }
            }

        }

        if (table_name.equalsIgnoreCase("R")) {
            int tableSize = R.r_relation.size();
            for (int i = 0; i < tableSize; i++) {
                String data = R.r_relation.get(i).getColData(col_name);
                if(bitMap.attributeValMap.containsKey(data)){
                    int rowIndex = bitMap.attributeValMap.get(data);
                    bitMap.bitVector.get(rowIndex).set(i,true);
                }
                else{
                    ArrayList<Boolean> newMap = new ArrayList<Boolean>(tableSize);
                    newMap.addAll(Collections.nCopies(tableSize, Boolean.FALSE));
                    int rows = bitMap.bitVector.size();
                    bitMap.attributeValMap.put(data,rows);
                    bitMap.bitVector.add(newMap);
                    bitMap.bitVector.get(rows).set(i,true);
                }
            }

        }

        /*if (table_name.equals("products")) {
            int tableSize = Products.products.size();
            for (int i = 0; i < tableSize; i++) {
                String data = Products.products.get(i).getColData(col_name);
                if(bitMap.attributeValMap.containsKey(data)){
                    int rowIndex = bitMap.attributeValMap.get(data);
                    bitMap.bitVector.get(rowIndex).set(i,true);
                }
                else{
                    ArrayList<Boolean> newMap = new ArrayList<Boolean>(tableSize);
                    newMap.addAll(Collections.nCopies(tableSize, Boolean.FALSE));
                    int rows = bitMap.bitVector.size();
                    bitMap.attributeValMap.put(data,rows);
                    bitMap.bitVector.add(newMap);
                    bitMap.bitVector.get(rows).set(i,true);
                }
            }

        }*/
        return bitMap;
    }

    public BitMap compressBitMap() {
        for(int i=0;i<bitVector.size();i++){
            bitVector.set(i,runLengthEncoding(bitVector.get(i)));
        }
        return this;
    }

    public BitMap decompressBitMap(){
        for(int i=0;i<bitVector.size();i++){
            bitVector.set(i,runLengthEncoding(bitVector.get(i)));
        }
        return this;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + attributeValMap.toString();
        str = str + "\n";
        int cnt=1;
        for(String attr:attributeValMap.keySet()){
            str = str +  " for value: " + attr + "\n";
            for(Boolean b: bitVector.get(attributeValMap.get(attr))){
                if(b==false){
                    str = str + cnt + "->0 ";
                }
                else {
                    str = str + cnt + "->1 ";
                }
                cnt++;
            }
            cnt=1;
            str = str + "\n";
        }
        return str;
    }
}
