package com.codewithdilan;

import java.util.ArrayList;
import java.util.List;

public class Slice {
    int noOfSlices;
    
    void createSlice(List<Character> listEventKey, List<Float> listEventValue, double canvasHeight, double canvasWidth){
        noOfSlices = listEventKey.size();
        float totalProb = 0;
        List<Float> listSlice = new ArrayList<Float>();
        List<String> listLegend = new ArrayList<String>();
        for(int i=0; i<noOfSlices; i++){
            totalProb = totalProb + listEventValue.get(i);
            listSlice.add(listEventValue.get(i));
            listLegend.add(sliceToString(listEventKey.get(i),listEventValue.get(i)));
        }
        listSlice.add(1-totalProb); 
        listLegend.add("All other letters, "+(1-totalProb));
        //System.out.println(listLegend.get(noOfSlices));
        
        for(int a = 0; a==noOfSlices; a++){
            //System.out.println(listLegend.get(a));
            //System.out.println(listSlice.get(a));
        }
        MyArc myarc = new MyArc();
        myarc.drawArc(listLegend, listSlice, canvasHeight, canvasWidth);
    }
    
    String sliceToString(char eventKey, float eventValue){
        String legend;
        legend = eventKey + ", "+ eventValue;
        return legend;
    }
    
    
}
