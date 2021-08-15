package ITEC3150;

/** ITEC3150 Final Project
 * authors Anthony Morse and Sarah Pak
 * Display stocks and portfolio
 */

import MiscClasses.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.File;
import java.math.BigDecimal;

public class Stonks extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //making objects
        Stock bb = YahooFinance.get("BB");
        Stock nokia = YahooFinance.get("NOK");
        Stock amc = YahooFinance.get("AMC");
        Stock gamestop = YahooFinance.get("GME");



        //get the prices
        BigDecimal bbPrice = bb.getQuote().getPrice();
        BigDecimal nokiaPrice = nokia.getQuote().getPrice();
        BigDecimal amcPrice = amc.getQuote().getPrice();
        BigDecimal gameStopPrice = gamestop.getQuote().getPrice();

        //get the changes
        BigDecimal bbChange = bb.getQuote().getChange();
        BigDecimal nokiaChange = nokia.getQuote().getChange();
        BigDecimal amcChange = amc.getQuote().getChange();
        BigDecimal gameStopChange = gamestop.getQuote().getChange();

        //printing to console to test
        System.out.println("BB stock price is "+ bbPrice);
        System.out.println("NOKIA stock price is "+ nokiaPrice);
        System.out.println("AMC stock price is "+ amcPrice);
        System.out.println("GAMESTOP stock price is "+ gameStopPrice);

        primaryStage.setTitle("Stock Graph");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Stocks");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Current Value");

        BarChart barChart = new BarChart(xAxis, yAxis);

        //label the key
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("BB" + "\n" + bbPrice + "\n" + "change: " + bbChange);
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("NOK" + "\n" + nokiaPrice+ "\n" + "change: " + nokiaChange);
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("AMC" + "\n" + amcPrice + "\n" + "change: " + amcChange);
        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("GME" + "\n" + gameStopPrice + "\n" + "change: " + gameStopChange);


        //add to graph
        dataSeries1.getData().add(new XYChart.Data("BB", bbPrice));
        dataSeries2.getData().add(new XYChart.Data("NOK", nokiaPrice));
        dataSeries3.getData().add(new XYChart.Data("AMC", amcPrice));
        dataSeries4.getData().add(new XYChart.Data("GME", gameStopPrice));
        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);
        barChart.getData().add(dataSeries3);
        barChart.getData().add(dataSeries4);

        VBox vbox = new VBox(addSharesForm());
        VBox vbox2 = new VBox(displaySharesForm());
        BorderPane bp = new BorderPane();
        bp.setLeft(barChart);
        bp.setRight(vbox);
        bp.setBottom(vbox2);

        Scene scene1 = new Scene(bp, 600, 400);

        primaryStage.setScene(scene1);
        primaryStage.setHeight(600);
        primaryStage.setWidth(1000);
        primaryStage.show();
    }

    int bbShares = 0;
    int nokiaShares = 0;
    int amcShares = 0;
    int gameStopShares = 0;

    public GridPane addSharesForm() {
        GridPane pane = new GridPane();
        Label bbLabel = new Label("shares of BB");
        TextField bbTextField = new TextField();
        Label nokiaLabel = new Label("shares of NOK");
        TextField nokiaTextField = new TextField();
        Label amcLabel = new Label("shares of AMC");
        TextField amcTextField = new TextField();
        Label gameStopLabel = new Label("shares of GME");
        TextField gameStopTextField = new TextField();
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(50, 50, 50, 50));
        Button updateButton = new Button("Update");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                bbShares += Integer.parseInt(bbTextField.getText());
                nokiaShares += Integer.parseInt(nokiaTextField.getText());
                amcShares += Integer.parseInt(amcTextField.getText());
                gameStopShares += Integer.parseInt(gameStopTextField.getText());
                System.out.println("you now have " + bbShares + " shares of BB");
                System.out.println("you now have " + nokiaShares + " shares of NOK");
                System.out.println("you now have " + amcShares + " shares of AMC");
                System.out.println("you now have " + gameStopShares + " shares of GME");
                displaySharesForm();
            }
        };
        updateButton.setOnAction(event);
        GridPane.setConstraints(bbLabel, 0, 0);
        GridPane.setConstraints(bbTextField, 1, 0);
        GridPane.setConstraints(amcLabel, 0, 1);
        GridPane.setConstraints(amcTextField, 1, 1);
        GridPane.setConstraints(nokiaLabel, 0, 2);
        GridPane.setConstraints(nokiaTextField, 1, 2);
        GridPane.setConstraints(gameStopLabel, 0, 3);
        GridPane.setConstraints(gameStopTextField, 1, 3);
        GridPane.setConstraints(updateButton, 0, 4);
        pane.getChildren().addAll(bbLabel, bbTextField, nokiaLabel, nokiaTextField, amcLabel, amcTextField, gameStopLabel, gameStopTextField, updateButton);
        return pane;
    }

    private GridPane displaySharesForm() {
        GridPane pane = new GridPane();
        Label youHave = new Label("You have: ");
        Label bbLabel = new Label(bbShares + " shares of BB");
        Label nokiaLabel = new Label(nokiaShares + " shares of NOK");
        Label amcLabel = new Label(amcShares + " shares of AMC");
        Label gameStopLabel = new Label(gameStopShares + " shares of GME");
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(50, 50, 50, 50));
        GridPane.setConstraints(youHave,0,0);
        GridPane.setConstraints(bbLabel, 0, 1);
        GridPane.setConstraints(amcLabel, 0, 2);
        GridPane.setConstraints(nokiaLabel, 0, 3);
        GridPane.setConstraints(gameStopLabel, 0, 4);
        pane.getChildren().addAll(youHave,bbLabel,nokiaLabel,amcLabel,gameStopLabel);
        return pane;
    }
}
