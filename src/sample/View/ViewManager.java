package sample.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.SuperiorManagement.RunnerManagement.Runner;
import sample.SuperiorManagement.StatsManagement.StatsManager;
import sample.SuperiorManagement.UtilityManagement.UtilityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ViewManager {

    public static void getInformationView(StackPane stackpanel, Pane mainpanel){
        double x=mainpanel.getLayoutX();
        double y=mainpanel.getLayoutY();
        double width=mainpanel.getWidth();
        double height=mainpanel.getHeight();

        System.out.println("Character Window is shown");
        stackpanel.requestFocus();

        //Action Label settings
        Label actionLabel = new Label("\nAtk:" + StatsManager.getAtk() +
                "\nHP:" + StatsManager.getHp() +
                "\nLevel:" + StatsManager.getLevel() +
                "\nClass:" + StatsManager.getClasstype() +
                "\n\n");

        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setAlignment(Pos.CENTER);
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.GREENYELLOW));

        //Name textfield settings
        TextField namefield = new TextField();
        namefield.setText(StatsManager.getName());
        namefield.setMaxWidth(150);
        namefield.setTranslateX(width / 2 - namefield.getMaxWidth() / 2);
        namefield.setTranslateY(height / 3);
        namefield.setAlignment(Pos.CENTER);

        //select all text on click
        namefield.setOnMouseClicked(actionEvent -> namefield.selectAll());

        //namefield typing operations
        namefield.setOnKeyReleased(actionEvent -> {
            //character limit is 10
            String curname = StatsManager.getName();
            if (namefield.getText().length() > 10) {
                namefield.setText(namefield.getText().substring(0, 10));
            }

            //list of permitted characters
            ArrayList<String> includedChars = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "p", "r", "s", "t", "u", "v",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Z",
                    "w", "x", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "_"));

            //permission condition
            boolean isIncluded = includedChars.indexOf(actionEvent.getText()) != -1;

            //check whether a new character is allowed
            if (!isIncluded) {
                namefield.setText(namefield.getText().replace(actionEvent.getText(), ""));
            }

            //save the name input to the system if it has changed
            if (actionEvent.getCode().equals(KeyCode.ENTER)) {
                if (!namefield.getText().equals(curname)) {
                    StatsManager.setName(namefield.getText());
                    System.out.println(StatsManager.getName());
                }
            }

            //set caret cursor position to last
            namefield.positionCaret(Math.min(namefield.getText().length() + 1, 10));
        });


        //Save instance of the tab
        Node contentSaved = stackpanel.getChildren().get(0);

        Button actionButton = new Button("Return");
        actionButton.setLayoutX(0);
        actionButton.setLayoutY(0);

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnAction(event -> {
            actionLabel.setVisible(false);
            stackpanel.getChildren().removeAll(actionLabel, namefield, actionButton);
            stackpanel.getChildren().add(contentSaved);
            stackpanel.setAlignment(Pos.CENTER);
        });

        //Remove focus from namefield
        actionLabel.setOnMouseClicked(mouseEvent -> actionLabel.requestFocus());

        //Initiate Battle Scene
        stackpanel.getChildren().remove(contentSaved);
        stackpanel.getChildren().addAll(actionLabel, namefield, actionButton);
        stackpanel.setAlignment(Pos.TOP_LEFT);
    }

}
