package com.codewithdilan;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class HistogramAlphaBet extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane root = new AnchorPane();

        Label lblDescription = new Label("Enter Number of Events:");
        AnchorPane.setBottomAnchor(lblDescription, 255.0);
        AnchorPane.setLeftAnchor(lblDescription, 50.0);
        AnchorPane.setRightAnchor(lblDescription, 310.0);

        TextField txtEvent = new TextField();
        txtEvent.setPrefSize(400, 25);
        AnchorPane.setBottomAnchor(txtEvent, 220.0);
        AnchorPane.setLeftAnchor(txtEvent, 50.0);
        AnchorPane.setRightAnchor(txtEvent, 50.0);

        Button btnGenerate = new Button("Generate Chart");
        btnGenerate.setPrefSize(400, 48);
        AnchorPane.setBottomAnchor(btnGenerate, 160.0);
        AnchorPane.setLeftAnchor(btnGenerate, 50.0);
        AnchorPane.setRightAnchor(btnGenerate, 50.0);

        Label lblErrorText = new Label("Enter Number of Events:");
        lblErrorText.setPrefWidth(400.0);
        AnchorPane.setBottomAnchor(lblErrorText, 130.0);
        AnchorPane.setLeftAnchor(lblErrorText, 50.0);
        AnchorPane.setRightAnchor(lblErrorText, 310.0);
        lblErrorText.setStyle("-fx-text-fill:" + "#0000FF");
        lblErrorText.setVisible(false);

        root.getChildren().add(lblDescription);
        root.getChildren().add(txtEvent);
        root.getChildren().add(btnGenerate);
        root.getChildren().add(lblErrorText);

        Scene scene = new Scene(root, 600, 400);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.setTitle("Alphabet Character Frequency Pie Chart");
        stage.show();

        btnGenerate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String filePath = "resources/Alice in Wonderland.txt";
                HistogramAlphaBet.MyPieChart pieChart = new HistogramAlphaBet.MyPieChart();

                try {
                    try {
                        int count = Integer.parseInt(txtEvent.getText().trim());
                        double canvasHeight, canvasWidth;
                        canvasHeight = root.getHeight();
                        canvasWidth = root.getWidth();
                        if (count <= 25) {
                            pieChart.characterCount(filePath, count, canvasHeight, canvasWidth);
//                            stage.close();
                        } else {
                            lblErrorText.setVisible(true);
                            lblErrorText.setText("Maximum number of events supported is 25!");
                        }

                    } catch (NumberFormatException e) {
                        lblErrorText.setVisible(true);
                        lblErrorText.setText("Invalid Input. Please enter a number between 0-26.");
                    }

                } catch (FileNotFoundException ex) {
                    lblErrorText.setVisible(true);
                    lblErrorText.setText("File Not Found!");
                    System.out.println("File Not Found: " + ex);
                }
            }
        });
    }

    public static class MyPieChart {

        void characterCount(String filePath, int count, double canvasHeight, double canvasWidth) throws FileNotFoundException {
            char[] strArray;
            int totalCount = 0;

            HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

            Scanner inFile = new Scanner(new FileReader(filePath));

            while (inFile.hasNextLine()) {

                strArray = inFile.nextLine().replaceAll("[^A-Za-z]+", "").toCharArray();

                for (char c : strArray) {
                    if (charCountMap.containsKey(c)) {
                        charCountMap.put(c, charCountMap.get(c) + 1);
                    } else {
                        charCountMap.put(c, 1);
                    }
                    totalCount++;
                }
            }

            Map<Character, Integer> sortedMap = charCountMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(e -> -e.getValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (a, b) -> {
                                throw new AssertionError();
                            },
                            LinkedHashMap::new
                    ));

            List<Character> listKey = new ArrayList<Character>(sortedMap.keySet());
            List<Integer> listValue = new ArrayList<Integer>(sortedMap.values());

            List<Character> arrayEventKey = new ArrayList<Character>();
            List<Float> arrayEventValue = new ArrayList<Float>();

            for (int i = 0; i < count; i++) {
                arrayEventKey.add(listKey.get(i));
                arrayEventValue.add(calculateProbability(listValue.get(i), totalCount));
            }

            Slice slice = new Slice();
            slice.createSlice(arrayEventKey, arrayEventValue, canvasHeight, canvasWidth);
        }

        float calculateProbability(int charCount, int totalCount) {
            float probability = 0;
            probability = (float) charCount / (float) totalCount;
            return probability;
        }

        void createChart(int noOfArc, float arcCenterX, float arcCenterY, float arcRadiusX, float arcRadiusY, List<Float> listArcStartAngle, List<Float> listArcLength, List<String> listLegend) throws Exception {
            Stage chartStage = new Stage();
            chartStage.setMinWidth(600);
            chartStage.setMinHeight(400);
            AnchorPane root = new AnchorPane();
            String color = "";
            float yOffSet = 0;

            for (int i = 0; i < noOfArc; i++) {
                color = MyColor.values()[i+1].getColorCode();
                Arc arc = new Arc();
                arc.setCenterX(arcCenterX + 45);
                arc.setCenterY(arcCenterY);
                arc.setRadiusX(arcRadiusX);
                arc.setRadiusY(arcRadiusY);
                arc.setStartAngle(listArcStartAngle.get(i));
                arc.setLength(listArcLength.get(i));
                arc.setType(ArcType.ROUND);
                arc.setFill(Color.web(color));

                Label lblLegend = new Label();
                lblLegend.setText(listLegend.get(i));
                lblLegend.setStyle("-fx-background-color:" + color);
                lblLegend.setPrefWidth(180);
                lblLegend.setPadding(new Insets(2, 2, 5, 5));
                lblLegend.setTextFill(Color.WHITE);

                lblLegend.setLayoutX(10);
                lblLegend.setLayoutY(yOffSet + 10);

                root.getChildren().add(arc);
                root.getChildren().add(lblLegend);
                yOffSet = yOffSet + 20;
            }

            Scene scene = new Scene(root, arcCenterX * 2, arcCenterY * 2);
            chartStage.setScene(scene);
            chartStage.setTitle("Alphabet Character Frequency Pie Chart");
            chartStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
