package ITEC3150;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Barchart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BarChart Experiments");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        BarChart  barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2021");
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("2022");

        dataSeries1.getData().add(new XYChart.Data("Desktop", 267));
        dataSeries1.getData().add(new XYChart.Data("Phone"  , 65));
        dataSeries1.getData().add(new XYChart.Data("Tablet"  , 23));
        dataSeries2.getData().add(new XYChart.Data("Desktop", 167));
        dataSeries2.getData().add(new XYChart.Data("Phone"  , 25));
        dataSeries2.getData().add(new XYChart.Data("Tablet"  , 13));

        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);

        VBox vbox = new VBox(barChart);

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(900);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}