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
import javafx.scene.layout.AnchorPane;
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

    public static void initInformationView(StackPane stackpanel, Pane mainpanel){
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

    //ACTION
    public static void initInnBattleScene(AnchorPane anchorPane, Tab towntab, List currencies, Class mainclass) {
        int x=0;
        int y=0;

        double width=anchorPane.getWidth();
        double height=anchorPane.getHeight();

        Label goldamount=(Label)currencies.get(0);
        Label diamondamount=(Label)currencies.get(1);
        ProgressBar energybar=(ProgressBar)currencies.get(2);

        //Log Message
        System.out.println("Inn Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("INN");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.SKYBLUE));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button returnButton = new Button("return");

        //nap
        ImageView ivnap = new ImageView(new Image(mainclass.getResource("/sample/Resources/couch.png").toExternalForm()));
        ivnap.setFitHeight(100);
        ivnap.setPreserveRatio(true);

        Label labelnap = new Label("", ivnap);
        labelnap.setPrefWidth(100);
        labelnap.setPrefHeight(100);
        labelnap.setTranslateX(width / 2 - labelnap.getPrefWidth() / 2);
        labelnap.setTranslateY(height / 2 - ivnap.getFitHeight() / 2);
        labelnap.setAlignment(Pos.CENTER);
        labelnap.setOpacity(0.5);
        labelnap.setTooltip(new Tooltip("--Nap--\nCost: 80 G\nEnergy Refill: 66%"));

        //sleep
        ImageView ivsleep = new ImageView(new Image(mainclass.getResource("/sample/Resources/bed.png").toExternalForm()));
        ivsleep.setFitHeight(100);
        ivsleep.setPreserveRatio(true);

        Label labelsleep = new Label("", ivsleep);
        labelsleep.setPrefWidth(100);
        labelsleep.setPrefHeight(100);
        labelsleep.setTranslateX(width / 2 - labelnap.getPrefWidth() / 2 + labelsleep.getPrefWidth() + 50);
        labelsleep.setTranslateY(height / 2 - ivsleep.getFitHeight() / 2);
        labelsleep.setAlignment(Pos.CENTER);
        labelsleep.setOpacity(0.5);
        labelsleep.setTooltip(new Tooltip("--Sleep--\nCost: 120 G\nEnergy Refill: 100%"));

        ///break
        ImageView ivbreak = new ImageView(new Image(mainclass.getResource("/sample/Resources/armchair.png").toExternalForm()));
        ivbreak.setFitHeight(100);
        ivbreak.setPreserveRatio(true);

        Label labelbreak = new Label("", ivbreak);
        labelbreak.setPrefWidth(100);
        labelbreak.setPrefHeight(100);
        labelbreak.setTranslateX(width / 2 - labelnap.getPrefWidth() / 2 - labelbreak.getPrefWidth() - 50);
        labelbreak.setTranslateY(height / 2 - ivbreak.getFitHeight() / 2);
        labelbreak.setAlignment(Pos.CENTER);
        labelbreak.setOpacity(0.5);
        labelbreak.setTooltip(new Tooltip("--Break--\nCost: 40 G\nEnergy Refill: 33%"));

        //Load saved instance of tab on leaving Battle Scene
        labelnap.setOnMouseReleased(mouseEvent -> {
            int costGold = 40;
            int currencyasgold = Integer.parseInt(goldamount.getText()) + Integer.parseInt(diamondamount.getText()) * 100;
            if (currencyasgold >= costGold) {
                addGoldListener(currencies,-costGold);
                double progress = energybar.getProgress() + 0.66;
                energybar.setProgress(Math.min(progress, 1));
            }
        });

        labelsleep.setOnMouseReleased(mouseEvent -> {
            int costGold = 120;
            int currencyasgold = Integer.parseInt(goldamount.getText()) + Integer.parseInt(diamondamount.getText()) * 100;
            if (currencyasgold >= costGold) {
                addGoldListener(currencies,-costGold);
                energybar.setProgress(1);
            }
        });

        labelbreak.setOnMouseReleased(mouseEvent -> {
            int costGold = 80;
            int currencyasgold = Integer.parseInt(goldamount.getText()) + Integer.parseInt(diamondamount.getText()) * 100;
            if (currencyasgold >= costGold) {
                addGoldListener(currencies,-costGold);
                double progress = energybar.getProgress() + 0.33;
                energybar.setProgress(Math.min(progress, 1));
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        //hover effects
        labelnap.setOnMouseEntered(mouseEvent -> labelnap.setOpacity(1));
        labelnap.setOnMouseExited(mouseEvent -> labelnap.setOpacity(0.5));
        //
        labelbreak.setOnMouseEntered(mouseEvent -> labelbreak.setOpacity(1));
        labelbreak.setOnMouseExited(mouseEvent -> labelbreak.setOpacity(0.5));
        //
        labelsleep.setOnMouseEntered(mouseEvent -> labelsleep.setOpacity(1));
        labelsleep.setOnMouseExited(mouseEvent -> labelsleep.setOpacity(0.5));


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel, titleLabel, labelbreak, labelnap, labelsleep, returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    public static void initGymBattleScene(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x=0;
        int y=0;

        double width=anchorPane.getWidth();
        double height=anchorPane.getHeight();

        Label goldamount=(Label)currencies.get(0);
        Label diamondamount=(Label)currencies.get(1);
        ProgressBar energybar=(ProgressBar)currencies.get(2);
        Label levelamount=(Label)currencies.get(3);

        //Log Message
        System.out.println("Gym Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("GYM");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTPINK));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("battle time!");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            if (energybar.getProgress() * 100 >= 25) {
                addExperienceListener(levelamount,(((int) (Math.random() * 5)) + 50) * Integer.parseInt(levelamount.getText()));
                addEnergyListener(energybar,-20);
                addGoldListener(currencies,(int) Math.round(200 * Integer.parseInt(levelamount.getText()) * 1.2));
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel, titleLabel, actionButton, returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private static void addGoldListener(List currencies, int change) {
        Label goldamount=(Label)currencies.get(0);
        Label diamondamount=(Label)currencies.get(1);
        ProgressBar energybar=(ProgressBar)currencies.get(2);

        if (change > 0)
            StatsManager.updateGoldIncrease(goldamount, diamondamount, change);
        else if (change < 0 && energybar.getProgress() < 1)
            StatsManager.updateGoldDecrease(goldamount, diamondamount, change);
    }

    private static void addExperienceListener(Label levelamount,int gain) {
        StatsManager.updateExperience(levelamount, gain);
    }

    private static void addEnergyListener(ProgressBar energybar,int change) {
        StatsManager.updateEnergyStatus(energybar, change);
    }
}
