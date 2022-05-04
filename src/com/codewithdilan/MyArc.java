package com.codewithdilan;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyArc extends MyShape {

    void drawArc(List<String> listLegend, List<Float> listSlice, double canvasHeight, double canvasWidth) {
        MyArc myOval = new MyArc();
        
        
        int noOfArc = listSlice.size();
        float arcStartAngle, arcLength;
        List<Float> listArcStartAngle = new ArrayList<Float>();
        List<Float> listArcLength = new ArrayList<Float>();
        
        myOval.setX1((float)canvasWidth / 2);
        myOval.setY1((float) canvasHeight / 2);
        if (canvasHeight < canvasWidth) {
            myOval.setX2((float) canvasHeight / 2-50);
            myOval.setY2((float) canvasHeight / 2-50);
        } else {
            myOval.setX2((float) canvasWidth / 2-50);
            myOval.setY2((float) canvasWidth / 2-50);
        }
        
        arcStartAngle = 90;
        arcLength = 0;
        
        for (int a = 0; a <noOfArc; a++) {
            
            arcLength = 360 * listSlice.get(a);
            listArcStartAngle.add(arcStartAngle);
            listArcLength.add(arcLength);
            
            arcStartAngle = arcStartAngle + arcLength;
        }

        HistogramAlphaBet.MyPieChart myPieChart = new HistogramAlphaBet.MyPieChart();

        try {
            myPieChart.createChart(noOfArc, myOval.getX1(), myOval.getY1(), myOval.getX2(), myOval.getY2(), listArcStartAngle, listArcLength, listLegend);
        } catch (Exception ex) {
            Logger.getLogger(MyArc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
