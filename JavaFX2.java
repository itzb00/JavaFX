package ITEC3150;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX2 extends Application {

    private Stage window;
    private Scene scene;
    private RadioButton[] coffeeRadioButtons = new RadioButton[2];
    private RadioButton[] muffinRadioButtons = new RadioButton[4];
    private CheckBox[] extrasBoxes = new CheckBox[5];
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Joe's Coffee House");

        //Checkboxes
        extrasBoxes[0] = new CheckBox("Cream");
        extrasBoxes[1] = new CheckBox("Sugar");
        extrasBoxes[2] = new CheckBox("Artificial Sweetener");
        extrasBoxes[3] = new CheckBox("Cinnamon");
        extrasBoxes[4] = new CheckBox("Caramel");

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(extrasBoxes[0], extrasBoxes[1], extrasBoxes[2], extrasBoxes[3], extrasBoxes[4]);

        //border pane
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(layout);
        createCoffeeOptions(mainLayout);
        createMuffinOptions(mainLayout);
        createButtons(mainLayout);
        scene = new Scene(mainLayout, 450, 350);
        window.setScene(scene);
        window.show();
    }

    //group where user can choose type of coffee
    private void createCoffeeOptions(BorderPane mainLayout) {
        ToggleGroup group = new ToggleGroup();
        RadioButton regular = new RadioButton("Regular");
        RadioButton decaf = new RadioButton("Decaf");
        coffeeRadioButtons[0] = regular;
        coffeeRadioButtons[1] = decaf;
        regular.setToggleGroup(group);
        decaf.setToggleGroup(group);
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.getChildren().addAll(regular,decaf);
        vbox.setPadding(new Insets(20,20,20,20));
        mainLayout.setLeft(vbox);
    }

    private void createMuffinOptions(BorderPane mainLayout) {
        ToggleGroup group = new ToggleGroup();
        RadioButton blueberry = new RadioButton("Blueberry");
        RadioButton chocolate = new RadioButton("Chocolate Chip");
        RadioButton bananaNut = new RadioButton("Banana Nut");
        RadioButton bran = new RadioButton("Bran");
        muffinRadioButtons[0] = blueberry;
        muffinRadioButtons[1] = chocolate;
        muffinRadioButtons[2] = bananaNut;
        muffinRadioButtons[3] = bran;
        blueberry.setToggleGroup(group);
        chocolate.setToggleGroup(group);
        bananaNut.setToggleGroup(group);
        bran.setToggleGroup(group);
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.getChildren().addAll(blueberry,chocolate, bananaNut, bran);
        vbox.setPadding(new Insets(20,20,20,20));
        mainLayout.setRight(vbox);
    }

    private void createButtons(BorderPane mainLayout) {
        Button calculate = new Button("Calculate");
        calculate.setOnAction(e -> calculateBill());
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> Platform.exit());
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(calculate,cancel);
        mainLayout.setBottom(hbox);
    }

    private void calculateBill() {
        double total = 0.00;
        //add check boxes for $0.25 each
        if (extrasBoxes[0].isSelected()) total += 0.25;
        if (extrasBoxes[1].isSelected()) total += 0.25;
        if (extrasBoxes[2].isSelected()) total += 0.25;
        if (extrasBoxes[3].isSelected()) total += 0.25;
        if (extrasBoxes[4].isSelected()) total += 0.25;
        if (muffinRadioButtons[0].isSelected()) total += 2.25;
        if (muffinRadioButtons[1].isSelected()) total += 2.25;
        if (muffinRadioButtons[2].isSelected()) total += 2.25;
        if (muffinRadioButtons[3].isSelected()) total += 2.25;
        if (coffeeRadioButtons[0].isSelected()) total += 3.00;
        if (coffeeRadioButtons[1].isSelected()) total += 3.00;
        total *= 1.07;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Total Price after 7% tax is: $" +  (double)Math.round(total * 100d) / 100d);
        alert.showAndWait();
    }

    //Handle checkbox options
    private void handleOptions(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4, CheckBox box5){
        calculateBill();
    }
}