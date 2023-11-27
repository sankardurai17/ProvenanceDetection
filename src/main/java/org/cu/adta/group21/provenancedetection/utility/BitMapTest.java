package org.cu.adta.group21.provenancedetection.utility;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BitMapTest {

    @Test
    public void runLengthEncoding() {
        String str = "0000000000000101";
        //String str = "00010010000000100000";
        ArrayList<Boolean> arr =new ArrayList<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '0')arr.add(false);
            else arr.add(true);
        }
        ArrayList<Boolean> res = BitMap.runLengthEncoding(arr);
        String resstr = "";
        for(Boolean b : res){
            if(b==false)resstr = resstr + "0";
            else resstr = resstr + "1";
        }
        assertEquals(resstr, "1110110101");
        //assertEquals("10111010110111", resstr);
    }

    @Test
    public void runLengthDecoding() {
        //String str = "1110110101";
        String str = "11011000";
        ArrayList<Boolean> arr =new ArrayList<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '0')arr.add(false);
            else arr.add(true);
        }
        ArrayList<Boolean> res = BitMap.runLengthDecoding(arr);
        String resstr = "";
        for(Boolean b : res){
            if(b==false)resstr = resstr + "0";
            else resstr = resstr + "1";
        }
        //assertEquals("0000000000000101",resstr);
        assertEquals("00000011",resstr);
    }
}