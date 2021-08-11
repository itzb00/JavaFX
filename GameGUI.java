package ITEC3150;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * Sarah Pak
 * GameGUI.java
 * Program that creates a window and text fields
 */
public class GameGUI extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("game gui");
        GridPane layout = new GridPane();

        createLabels(layout);
        createFields(layout);
        createButton(layout);
        adjustLayout(layout);


        primaryStage.setScene(new Scene(layout, 500, 500));
        primaryStage.show();
    }

    private void adjustLayout(GridPane layout) {

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
    }

    private void createButton(GridPane layout) {

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");
        layout.add(save,0,6);
        layout.add(cancel,1,6);
        GridPane.setHalignment(save, HPos.CENTER);
        GridPane.setHalignment(cancel, HPos.CENTER);
    }

    private void createLabels(GridPane layout) {

        layout.add(new Label("Title"),0,0);
        layout.add(new Label("System"),0,1);
        layout.add(new Label("Developer"),0,2);
        layout.add(new Label("Category"),0,3);
        layout.add(new Label("Rating"),0,4);
        layout.add(new Label("Year"),0,5);

    }

    private void createFields(GridPane layout) {

        layout.add(new TextField("Title"),1,0);
        layout.add(new TextField("System"),1,1);
        layout.add(new TextField("Developer"),1,2);
        layout.add(new TextField("Category"),1,3);
        layout.add(new TextField("Rating"),1,4);
        layout.add(new TextField("Year"),1,5);
    }


    public static void main(String[] args) {
        launch(args);
    }

}