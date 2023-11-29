package org.cu.adta.group21.provenancedetection.utility;

import org.cu.adta.group21.provenancedetection.Main;
import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.S;

import java.util.*;
import java.util.stream.Collectors;

public class SortMergeJoin {
    public static void joinSortedLists() {
        List<R> rList = R.r_relation;
        List<S> sList = S.s_relation;
        Map<String, String> map = new LinkedHashMap<>();
        //map.put("S.NO A2 A3 ","R_Ann.S_Ann");
        boolean flag=false;
        for (int i = 0; i < rList.size(); i++) {
            R r = rList.get(i);
            StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(r.a2).append(" ").append(r.a3);
            flag=false;
            for (int j = 0; j < sList.size(); j++) {
                S s = sList.get(j);
                if (r.a2 == s.b2 && r.a3 == s.b3) {
                    flag=true;
                    StringBuilder valueBuilder = new StringBuilder();
                    valueBuilder.append(r.ann).append(".").append(s.ann);
                    if (map.containsKey(keyBuilder.toString())) {
                        map.put(keyBuilder.toString(), (map.get(keyBuilder.toString()) + " + " + valueBuilder.toString()));
                    } else {
                        map.put(keyBuilder.toString(), valueBuilder.toString());
                    }
                }
                else if(flag){
                    break;
                }
            }
        }
        Main.displayResult(map);
    }
}
