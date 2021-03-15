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
import sample.Main;
import sample.Model.GunnerManagement.Gunner;
import sample.Model.RunnerManagement.Runner;
import sample.Model.StageManagement.FleetStage;
import sample.Model.StatsManagement.StatsManager;
import sample.Model.UtilityManagement.MediaManager;
import sample.Model.UtilityManagement.UtilityManager;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ViewManager {

    //Information View
    public static void initSettingsView(StackPane stackpanel) {
        System.out.println("Settings Window is shown");

        //Save instance of the tab
        Node contentSaved = stackpanel.getChildren().get(0);
        stackpanel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTGOLDENRODYELLOW));

        String skipURL = "/soundfile/icons/player/skip.png";
        String stopURL = "/soundfile/icons/player/stop.png";

        Label songtitle = new Label(titlename());
        songtitle.setTranslateX(0);
        songtitle.setTranslateY(stackpanel.getHeight() / 4);
        songtitle.setPrefWidth(stackpanel.getWidth());
        songtitle.setContentDisplay(ContentDisplay.CENTER);
        songtitle.setAlignment(Pos.CENTER);

        Timeline timer = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), event -> {
            if (!MediaManager.isMediaPlayerInActive()) {
                songtitle.setText(titlename());

            }
        });
        timer.getKeyFrames().add(keyFrame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        ImageView playview = new ImageView(getVolumeOffImg());
        playview.setFitWidth(25);
        playview.setPreserveRatio(true);

        if (!MediaManager.isMediaPlayerInActive() && MediaManager.isPlayIconValid()) {
            playview.setImage(getVolumeOnImg());
        }

        ImageView skipview = new ImageView(new Image(Main.class.getResource("/sample/Resources" + skipURL).toExternalForm()));
        skipview.setFitWidth(25);
        skipview.setPreserveRatio(true);

        ImageView stopview = new ImageView(new Image(Main.class.getResource("/sample/Resources" + stopURL).toExternalForm()));
        stopview.setFitWidth(25);
        stopview.setPreserveRatio(true);

        Label playLabel = new Label("", playview);
        playLabel.setTranslateX(stackpanel.getWidth() / 3);
        playLabel.setTranslateY(stackpanel.getHeight() / 3);
        playLabel.setContentDisplay(ContentDisplay.RIGHT);
        playLabel.setId("Play");

        if (!MediaManager.isMediaPlayerInActive() && MediaManager.isPlayIconValid()) {
            playLabel.setId("Pause");
        }

        Label skipLabel = new Label("", skipview);
        skipLabel.setTranslateX(stackpanel.getWidth() / 3 + 100);
        skipLabel.setTranslateY(stackpanel.getHeight() / 3);
        skipLabel.setContentDisplay(ContentDisplay.RIGHT);

        Label stopLabel = new Label("", stopview);
        stopLabel.setTranslateX(stackpanel.getWidth() / 3 + 200);
        stopLabel.setTranslateY(stackpanel.getHeight() / 3);
        stopLabel.setContentDisplay(ContentDisplay.RIGHT);

        Label toggleOptions = new Label("Options:");
        toggleOptions.setTranslateX(stackpanel.getWidth() / 3);
        toggleOptions.setTranslateY(stackpanel.getHeight() / 3 + 50);

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton autobtn = new RadioButton("Autoplay");

        autobtn.setToggleGroup(toggleGroup);
        autobtn.setTranslateX(toggleOptions.getTranslateX() + 50);
        autobtn.setTranslateY(toggleOptions.getTranslateY() + 25);
        autobtn.setContentDisplay(ContentDisplay.RIGHT);

        RadioButton replaybtn = new RadioButton("Replay");
        replaybtn.setToggleGroup(toggleGroup);
        replaybtn.setTranslateX(toggleOptions.getTranslateX() + 50);
        replaybtn.setTranslateY(autobtn.getTranslateY() + 50);
        replaybtn.setContentDisplay(ContentDisplay.RIGHT);

        RadioButton shufflebtn = new RadioButton("Shuffle");
        shufflebtn.setToggleGroup(toggleGroup);
        shufflebtn.setTranslateX(toggleOptions.getTranslateX() + 50);
        shufflebtn.setTranslateY(replaybtn.getTranslateY() + 50);
        shufflebtn.setContentDisplay(ContentDisplay.RIGHT);

        if (MediaManager.isMediaPlayerInActive()) {
            shufflebtn.setSelected(true);
            MediaManager.setARS(3);
        } else {
            switch ( MediaManager.getARS() ) {
                case 1:
                    autobtn.setSelected(true);
                    break;
                case 2:
                    replaybtn.setSelected(true);
                    break;
                case 3:
                    shufflebtn.setSelected(true);
                    break;
            }
        }

        toggleGroup.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();

            if (rb != null) {
                if (rb == autobtn) {
                    System.out.println("auto");
                    MediaManager.setARS(1);
                } else if (rb == replaybtn) {
                    System.out.println("replay");
                    MediaManager.setARS(2);
                } else if (rb == shufflebtn) {
                    System.out.println("shuffle");
                    MediaManager.setARS(3);
                }
            }
        });

        playLabel.setOnMouseReleased(mouseEvent -> {
            if (MediaManager.getARS() == -1) return;

            if (playLabel.getId().equals("Play")) {
                playLabel.setId("Pause");

                playview.setImage(getVolumeOnImg());

                MediaManager.run();
            } else {
                playLabel.setId("Play");

                playview.setImage(getVolumeOffImg());

                MediaManager.pause();
            }
        });

        skipLabel.setOnMouseReleased(mouseEvent -> {
            if (MediaManager.getARS() == -1) return;

            playLabel.setId("Pause");
            playview.setImage(getVolumeOnImg());
            MediaManager.skip();
        });

        stopLabel.setOnMouseReleased(mouseEvent -> {
            if (MediaManager.getARS() == -1) return;

            playLabel.setId("Play");
            playview.setImage(getVolumeOffImg());
            MediaManager.stop();
        });

        Button actionButton = new Button("Return");
        actionButton.setLayoutX(0);
        actionButton.setLayoutY(0);

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnAction(event -> {
            stackpanel.getChildren().removeAll(actionButton, songtitle, playLabel, skipLabel, stopLabel, toggleOptions, autobtn, shufflebtn, replaybtn);
            stackpanel.getChildren().add(contentSaved);
            stackpanel.setAlignment(Pos.CENTER);
        });

        //Initiate Battle Scene
        stackpanel.getChildren().remove(contentSaved);
        stackpanel.getChildren().addAll(actionButton, songtitle, playLabel, skipLabel, stopLabel, toggleOptions, autobtn, shufflebtn, replaybtn);
        stackpanel.setAlignment(Pos.TOP_LEFT);
    }

    public static void initInformationView(StackPane stackpanel, Pane mainpanel) {
        double x = mainpanel.getLayoutX();
        double y = mainpanel.getLayoutY();
        double width = mainpanel.getWidth();
        double height = mainpanel.getHeight();

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
    public static void initInnView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);

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
        ImageView ivnap = new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/couch.png").toExternalForm()));
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
        ImageView ivsleep = new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/bed.png").toExternalForm()));
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
        ImageView ivbreak = new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/armchair.png").toExternalForm()));
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
                addGoldListener(currencies, -costGold);
                double progress = energybar.getProgress() + 0.66;
                energybar.setProgress(Math.min(progress, 1));
            }
        });

        labelsleep.setOnMouseReleased(mouseEvent -> {
            int costGold = 120;
            int currencyasgold = Integer.parseInt(goldamount.getText()) + Integer.parseInt(diamondamount.getText()) * 100;
            if (currencyasgold >= costGold) {
                addGoldListener(currencies, -costGold);
                energybar.setProgress(1);
            }
        });

        labelbreak.setOnMouseReleased(mouseEvent -> {
            int costGold = 80;
            int currencyasgold = Integer.parseInt(goldamount.getText()) + Integer.parseInt(diamondamount.getText()) * 100;
            if (currencyasgold >= costGold) {
                addGoldListener(currencies, -costGold);
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

    public static void initGymView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

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
                addExperienceListener(levelamount, (((int) (Math.random() * 5)) + 50) * Integer.parseInt(levelamount.getText()));
                addEnergyListener(energybar, -20);
                addGoldListener(currencies, (int) Math.round(200 * Integer.parseInt(levelamount.getText()) * 1.2));
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

    private static int red = (int) (Math.random() * 256), green = (int) (Math.random() * 256), blue = (int) (Math.random() * 256);
    private static boolean isToggleupRed = true, isToggleupGreen = false, isToggleupBlue = true;
    private static int dailydojoattempts = 2;

    public static void initDojoView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("Dojo Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("DOJO");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();

        //color transition for background of dojo battle scene
        double duration = 10;

        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(duration), event -> {
                    //transition of red
                    if (red >= 0 && red < 256) {
                        if (red == 0)
                            isToggleupRed = true;
                        else if (red == 255)
                            isToggleupRed = false;

                        if (isToggleupRed)
                            red++;
                        else {
                            red--;

                        }
                    }
                    //transition of green
                    if (green >= 0 && green < 256) {
                        if (green == 0)
                            isToggleupGreen = true;
                        else if (green == 255)
                            isToggleupGreen = false;

                        if (isToggleupGreen)
                            green++;
                        else {
                            green--;
                        }
                    }
                    //transition of blue
                    if (blue >= 0 && blue < 256) {
                        if (blue == 0)
                            isToggleupBlue = true;
                        else if (blue == 255)
                            isToggleupBlue = false;

                        if (isToggleupBlue)
                            blue++;
                        else {
                            blue--;
                        }
                    }

                    actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.rgb(red, green, blue)));
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("attempt: " + dailydojoattempts);
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            if (dailydojoattempts > 0 && energybar.getProgress() * 100 >= 50) {
                //update loots
                addExperienceListener(levelamount, (((int) (Math.random() * 500 * Integer.parseInt(levelamount.getText()))) + 50) * Integer.parseInt(levelamount.getText()));
                addGoldListener(currencies, (int) Math.round(200 * Integer.parseInt(levelamount.getText()) * 0.2));
                addEnergyListener(energybar, -50);

                //update attempts
                dailydojoattempts--;
                actionButton.setText("attempt: " + dailydojoattempts);
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            timer.pause();
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel, titleLabel, actionButton, returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);

        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private static int thedarkportalstage = 1;

    public static void initTheDarkPortalView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("The Dark Portal Window is Activated");

        //Title Label
        Label titleLabel = new Label("THE DARK PORTAL");
        titleLabel.setPrefWidth(200);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        //Background Label
        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIME));
        actionLabel.setTranslateX(x);
        actionLabel.setTranslateY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setAlignment(Pos.CENTER);

        //Effect Label
        ImageView iveffect = new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/slash.gif").toExternalForm()));

        Label effectlabel = new Label("", iveffect);
        effectlabel.setPrefWidth(100);
        effectlabel.setPrefHeight(178);
        effectlabel.setTranslateX(width / 2 - 9 * effectlabel.getPrefWidth() / 8);
        effectlabel.setTranslateY(height / 2 - effectlabel.getPrefHeight() / 2);
        effectlabel.setAlignment(Pos.CENTER);

        effectlabel.setVisible(false);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("Stage Battle" + thedarkportalstage);
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            /**Two separate clicks handled at the same time, new click starts a new instance and doesnt overwrite previous click
             System.out.println("Primary button: "+!mouseEvent.isPrimaryButtonDown());
             System.out.println("Secondary button: "+!mouseEvent.isSecondaryButtonDown());
             */

            //One click handler at a time, when another clic happens, previous click is terminated
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                //function1: on left click
                System.out.println("Primary button is clicked");

                //Battle
                if (actionButton.getText().startsWith("Stage Battle")) {
                    if (energybar.getProgress() * 100 >= 15) {
                        //stage battle
                        addGoldListener(currencies, ((int) (Math.random() * 15) + 35) * thedarkportalstage);
                        addExperienceListener(levelamount, ((int) (Math.random() * 15) + 15) * thedarkportalstage);
                        addEnergyListener(energybar, -15);
                        effectlabel.setVisible(true);

                        //update button for next stage
                        actionButton.setText("Next");

                        //disable next stage access for 3 seconds
                        double duration = 5;
                        actionButton.setDisable(true);
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(duration), event -> {
                            actionButton.setDisable(false);
                            effectlabel.setVisible(false);
                        }));
                        timer.setCycleCount(Timeline.INDEFINITE);
                        timer.play();

                    }
                } else if (actionButton.getText().equals("Next")) {
                    //update text for the current stage
                    actionButton.setText("Stage Battle " + ++thedarkportalstage);
                }
            }

            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                //function2: on right click
                System.out.println("Secondary button is clicked");
            }

            if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                //function3: on wheel click
                System.out.println("Middle button is clicked");
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel, titleLabel, effectlabel, actionButton, returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    public static void initTownSquareView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("Town Square Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("TOWN SQUARE");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTGRAY));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

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

    public static void initWellspringView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("Wellspring Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("WELLSPRING");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTCORAL));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

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

    public static void initBarrackView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("Barrack Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("BARRACK");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTSTEELBLUE));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

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

    public static void initTheGreatestWallView(AnchorPane anchorPane, Tab towntab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("The Greatest Wall Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("THE GREATEST WALL");
        titleLabel.setPrefWidth(200);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.LIGHTCYAN));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

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

    public static void initCityHallView(AnchorPane anchorPane, Tab towntab, TabPane maintab, List currencies) {
        int x = 0;
        int y = 0;

        double width = anchorPane.getWidth();
        double height = anchorPane.getHeight();

        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);
        Label levelamount = (Label) currencies.get(3);

        //Log Message
        System.out.println("City Hall Window is Activated");
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_LEFT);

        //Action Label settings
        Label titleLabel = new Label("CITY HALL");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width / 2 - titleLabel.getPrefWidth() / 2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.YELLOW));
        actionLabel.setTranslateX(x);
        actionLabel.setTranslateY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("start!");
        actionButton.setPrefSize(100, 28);

        actionButton.setTranslateX(width / 2 - actionButton.getPrefWidth() / 2);
        actionButton.setTranslateY(height - 35);

        Button returnButton = new Button("return");

        //speed button presets
        Button speedButton = new Button("Off");
        speedButton.setPrefSize(35, 35);

        speedButton.setTranslateX(width - speedButton.getPrefWidth());
        speedButton.setTranslateY(actionButton.getTranslateY());
        speedButton.setDisable(true);

        //vehicle button presets
        Button vehicleButton = new Button("Car");
        vehicleButton.setPrefSize(70, 35);

        vehicleButton.setTranslateX(width - speedButton.getPrefWidth() - vehicleButton.getPrefWidth());
        vehicleButton.setTranslateY(actionButton.getTranslateY());
        vehicleButton.setDisable(true);

        //RUNNER CLASS
        Runner runnerLabel = new Runner(maintab, width, height, actionButton, speedButton, vehicleButton, towntab, stackPane);
        speedButton.setOnMouseClicked(mouseEvent -> {
            if (speedButton.getText().equals("On")) {
                speedButton.setText("Off");
                runnerLabel.setSpeed(1);
            } else if (speedButton.getText().equals("Off")) {
                speedButton.setText("On");
                runnerLabel.setSpeed(5);
            }
        });

        vehicleButton.setOnMouseClicked(mouseEvent -> {
            switch ( vehicleButton.getText() ) {
                case "Car":
                    vehicleButton.setText("Jeep");
                    runnerLabel.setJeepView();
                    break;
                case "Jeep":
                    vehicleButton.setText("Truck");
                    runnerLabel.setTruckView();
                    break;
                case "Truck":
                    vehicleButton.setText("Car");
                    runnerLabel.setCarView();
                    break;
            }
        });

        //GUNNER CLASS
        Gunner gunnerlabel= new Gunner(maintab,width,height,actionButton,towntab,stackPane);
        Timeline gunfire = new Timeline();
        KeyFrame gunkeyframe = new KeyFrame(Duration.seconds(1),event -> {
            if(runnerLabel.isVisible())
                gunnerlabel.fire(runnerLabel);
        });
        gunfire.getKeyFrames().add(gunkeyframe);
        gunfire.setCycleCount(Timeline.INDEFINITE);
        gunfire.play();

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        stackPane.getChildren().addAll(actionLabel, titleLabel, gunnerlabel, runnerLabel, actionButton, speedButton, vehicleButton, returnButton);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    // CURRENCY LISTENERS
    private static void addGoldListener(List currencies, int change) {
        Label goldamount = (Label) currencies.get(0);
        Label diamondamount = (Label) currencies.get(1);
        ProgressBar energybar = (ProgressBar) currencies.get(2);

        if (change > 0)
            StatsManager.updateGoldIncrease(goldamount, diamondamount, change);
        else if (change < 0 && energybar.getProgress() < 1)
            StatsManager.updateGoldDecrease(goldamount, diamondamount, change);
    }

    private static void addExperienceListener(Label levelamount, int gain) {
        StatsManager.updateExperience(levelamount, gain);
    }

    private static void addEnergyListener(ProgressBar energybar, int change) {
        StatsManager.updateEnergyStatus(energybar, change);
    }


    //// VOLUME ICONS ///
    private static Image getVolumeOnImg() {
        String volumeonURL = "soundfile/icons/player/volume-on.png";

        return new Image(Main.class.getResource("/sample/Resources/" + volumeonURL).toExternalForm());
    }

    private static Image getVolumeOffImg() {
        String volumeoffURL = "soundfile/icons/player/volume-off.png";

        return new Image(Main.class.getResource("/sample/Resources/" + volumeoffURL).toExternalForm());
    }

    // SONG TITLE //
    private static String titlename(){
        if(MediaManager.isMediaPlayerInActive())
            return "";
        return MediaManager.getCurrentSongName();
    }

    /// FLEET WINDOW ///
    private static boolean check=false;
    public static void addFleetWindowTogglerListener(Pane fleet_fullscreen) throws IOException {
        if(!check) {
            //make a clone view of Fleet View to run a Fleet Window, close the Primary Window
            check=true;
            new FleetStage();
            Main.getPrimaryStage().hide();
        }
        else {
            //close the Fleet Window and show the Primary Window
            check=false;
            Main.getPrimaryStage().show();
            fleet_fullscreen.getScene().getWindow().hide();
        }
    }
}
