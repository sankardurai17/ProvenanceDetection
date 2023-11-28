package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.Main;
import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.Result;
import org.cu.adta.group21.provenancedetection.model.S;

import java.util.*;

public class SortMergeJoin {
        /*public static void joinSortedLists(){
        int i=0, j=0;
        List<R> rList= R.r_relation;
        List<S> sList= S.s_relation;
        List< Result> res=new ArrayList<>();
        while(i<rList.size()&&j<sList.size()){
            R r = rList.get(i);
            S s=sList.get(j);
            if(r.a2==s.b2 && r.a3 == s.b3){
                while (j<sList.size() && r.a2==sList.get(j).b2 && r.a3 == sList.get(j).b3)
                {
                    Result result=new Result(r.a2,r.a3,r.ann, sList.get(j).ann);
                    res.add(result);
                    j++;
                }
                i++;
            }
            else if(r.a2<s.b2){
                i++;
            }
            else{
                j++;
            }

        }
provenanceCheck(res);
    }

    public static void provenanceCheck(List<Result> res){
            Map<String,String> stringMap=new LinkedHashMap<>();
            for(int i=0;i<res.size();i++){
                Result row=res.get(i);
                String key=row.A2+" "+row.A3;
                String value=row.ann1+"+"+row.ann2;
                if(stringMap.containsKey(key)){
                    stringMap.put(key,stringMap.get(key)+"."+value);
                }
                else{
                    stringMap.put(key,value);
                }
            }
            Main.displayResult(stringMap);
    }*/

    public static void joinSortedLists() {
        List<R> rList = R.r_relation;
        List<S> sList = S.s_relation;
        Map<String, String> map = new LinkedHashMap<>();
        //map.put("S.NO A2 A3 ","R_Ann.S_Ann");
        for (int i = 0; i < rList.size(); i++) {
            R r = rList.get(i);
            StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(r.a2 + " ").append(r.a3);
            for (int j = 0; j < sList.size(); j++) {
                StringBuilder valueBuilder = new StringBuilder();
                S s = sList.get(j);
                if (r.a2 == s.b2 && r.a3 == s.b3) {
                    valueBuilder.append(r.ann).append(".").append(s.ann);
                    if (map.containsKey(keyBuilder.toString())) {
                        map.put(keyBuilder.toString(), (map.get(keyBuilder.toString()) + " + " + valueBuilder.toString()));
                    } else {
                        map.put(keyBuilder.toString(), valueBuilder.toString());
                    }
                }
            }
        }
        Main.displayResult(map);
    }
}
