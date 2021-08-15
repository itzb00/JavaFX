package ITEC3150;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFX1 extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("JavaFX1 - Assignment");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Title:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField titleInput = new TextField();
        titleInput.setPromptText("...");
        GridPane.setConstraints(titleInput, 1, 0);

        //System Label
        Label systemLabel = new Label("System:");
        GridPane.setConstraints(systemLabel, 0, 1);

        //System Input
        TextField systemInput = new TextField();
        systemInput.setPromptText("...");
        GridPane.setConstraints(systemInput, 1, 1);

        //Developer Label
        Label devLabel = new Label("Developer:");
        GridPane.setConstraints(devLabel, 0, 2);

        //Developer Input
        TextField devInput = new TextField();
        devInput.setPromptText("...");
        GridPane.setConstraints(devInput, 1, 2);

        Label catLabel = new Label("Category:");
        GridPane.setConstraints(catLabel, 0, 3);

        //Category Input
        TextField catInput = new TextField();
        devInput.setPromptText("...");
        GridPane.setConstraints(catInput, 1, 3);

        Label rateLabel = new Label("Rating:");
        GridPane.setConstraints(rateLabel, 0, 4);

        //Category Input
        TextField rateInput = new TextField();
        devInput.setPromptText("...");
        GridPane.setConstraints(rateInput, 1, 4);

        Label yearLabel = new Label("Year:");
        GridPane.setConstraints(yearLabel, 0, 5);

        //Category Input
        TextField yearInput = new TextField();
        devInput.setPromptText("...");
        GridPane.setConstraints(yearInput, 1, 5);


        //save
        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton,0, 7);
        saveButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nice game, I saved it.");
            alert.showAndWait();
        });

        //cancel
        Button cancelButton = new Button("Cancel");
        GridPane.setConstraints(cancelButton, 1,7);
        cancelButton.setOnAction(e -> Platform.exit());

        //Add everything to grid
        grid.getChildren().addAll(nameLabel, titleInput, systemLabel, systemInput, devLabel, devInput, catLabel, catInput, rateLabel,
                rateInput, yearLabel, yearInput, saveButton, cancelButton);

        Scene scene = new Scene(grid, 600, 400);
        window.setScene(scene);
        window.show();
    }
}