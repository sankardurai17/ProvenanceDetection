package org.cu.adta.group21.provenancedetection;

import java.util.Map;
import java.util.Scanner;

import org.cu.adta.group21.provenancedetection.model.R;
import org.cu.adta.group21.provenancedetection.model.S;
import org.cu.adta.group21.provenancedetection.utility.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Main class
 *
 * */
public class Main {
    public static void main(String[] args) {
        int choice = displayWelcomePage();
        loadRelations();
        if (choice == 1) {
            long timeForSort = System.currentTimeMillis();
            //Collections.sort, comparator was overridden based
            Collections.sort(R.r_relation);
            Collections.sort(S.s_relation);
            long timeForEnd = System.currentTimeMillis();
            System.out.println("Time taken to sort two lists is: " + (timeForEnd - timeForSort) + " in milli seconds");
            long startTimeForJoinAndProv = System.currentTimeMillis();
            //This method does the join and provenance calculation part
            SortMergeJoin.joinSortedLists();
            long endTimeForJoinAndProv = System.currentTimeMillis();
            System.out.println("Time taken to join and detect provenance on two lists is: " + (endTimeForJoinAndProv - startTimeForJoinAndProv) + " in milli seconds");
        } else {
            //join using bitmap;
            //Query :: "SELECT A2, A3 FROM R, S WHERE R.A2 = S.B2 AND R.A3 = S.B3;"
            //generate compressed bitmaps on Join attributes:: r.a2, s.b2, r.a3, s.b3:

            long startTimeForBitmapCreation = System.currentTimeMillis();
            BitMap ra2x = BitMap.bitMapIndex("a2", "r").compressBitMap();
            BitMap sb2x = BitMap.bitMapIndex("b2", "s").compressBitMap();
            BitMap ra3x = BitMap.bitMapIndex("a3", "r").compressBitMap();
            BitMap sb3x = BitMap.bitMapIndex("b3", "s").compressBitMap();
            long endTimeForBitmapCreation = System.currentTimeMillis();
            System.out.println("Time taken to create bit map indexes is: " + (endTimeForBitmapCreation - startTimeForBitmapCreation) + " in milli seconds");

            //perform join:
            long startTimeForJoinBitMaps = System.currentTimeMillis();
            BitMapJoin.joinUsingBitmap(ra2x, sb2x, ra3x, sb3x);
            long endTimeForJoinBitMaps = System.currentTimeMillis();
            System.out.println("Time taken to join bit map indexes is: " + (endTimeForJoinBitMaps - startTimeForJoinBitMaps) + " in milli seconds");

        }
    }

    public static int displayWelcomePage() {
        System.out.println("Hello and Welcome!! Press 1.Join using sort 2.Join using bitmap");
        Scanner sc = new Scanner(System.in);
        int ip = sc.nextInt();
        return ip;
    }

    private static void loadRelations() {
        String qry = "select a2, a3 from r, s where r.a2 = s.b2 AND r.a3 = s.b3";
        R.loadData();
        S.loadData();
    }

    public static void displayResult(Map<String, String> resultMap) {
        AtomicInteger i = new AtomicInteger(1);
        resultMap.forEach((key, value) -> System.out.println((i.getAndIncrement()) + " " + key + " " + value));
    }
}
