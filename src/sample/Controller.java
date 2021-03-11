package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.SuperiorManagement.RunnerManagement.Runner;
import sample.SuperiorManagement.StatsManagement.StatsManager;
import sample.SuperiorManagement.UtilityManagement.UtilityManager;
import sample.View.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    ////////////////////// CONTAINERS //////////////////////
    @FXML
    private StackPane stackpanel;
    @FXML
    private Pane mainpanel;
    @FXML
    private TabPane maintab;

    ////////////////////// LOCATIONS //////////////////////
    @FXML
    private Tab towntab;
    @FXML
    private Tab dungeontab;
    @FXML
    private Tab colessiumntab;
    @FXML
    private Tab storetab;

    ////////////////////// TOWN //////////////////////
    @FXML
    private Label inn;
    @FXML
    private Label thedarkportal;
    @FXML
    private Label cityhall;
    @FXML
    private Label dojo;
    @FXML
    private Label gym;
    @FXML
    private Label thegreatestwall;
    @FXML
    private Label townsquare;
    @FXML
    private Label wellspring;
    @FXML
    private Label barracks;

    //StatsManager instance

    //Set Initial Values for the Character
    public void setCharacterBaseStats() {
        StatsManager.setCharacterBaseStats();
    }

    //Change Values for the Character
    public void updateCharacterInfo(String name, String classname, int atk, int hp) {
        if (!name.isEmpty())
            StatsManager.setName(name);

        if (!classname.isEmpty())
            StatsManager.setClasstype(classname);

        if (atk > 0 && hp > 0) {
            StatsManager.setAtk(atk);
            StatsManager.setHp(hp);
        }
    }

    public void updateCharacterInfo(String name, String classname, int atk, int hp, int level) {
        if (!name.isEmpty())
            StatsManager.setName(name);

        if (!classname.isEmpty())
            StatsManager.setClasstype(classname);

        if (level >= 0)
            StatsManager.setLevel(level);

        if (atk > 0 && hp > 0) {
            StatsManager.setAtk(atk);
            StatsManager.setHp(hp);
        }
    }

    //Access Game Tabs
    public List<Tab> getGameTabs() {
        return Arrays.asList(towntab, dungeontab, colessiumntab, storetab);
    }

    //Access Game Tab Containers
    public List<AnchorPane> getGameTabContents() {
        return Arrays.asList((AnchorPane) towntab.getContent(), (AnchorPane) dungeontab.getContent(), (AnchorPane) colessiumntab.getContent(), (AnchorPane) storetab.getContent());
    }

    //Access Buildings in Town Tab
    public List<Label> getTownBuildings() {
        AnchorPane townAnchorPane = (AnchorPane) towntab.getContent();

        List<Label> resultlist = new ArrayList<>();
        for (Node node : townAnchorPane.getChildren()) {
            resultlist.add((Label) node);
        }

        return resultlist;
    }

    //Access a Specific Building in Town Tab
    public Label getTownBuilding(String name) {
        for (Label label : getTownBuildings()) {
            if (label.getText().toLowerCase().equals(name.toLowerCase()))
                return label;
        }
        return null;
    }

    //Change Name of a Specific Building in Town Tab
    public void setTownBuildingName(String name, String newname) {
        getTownBuilding(name).setText(newname);
    }

    ////////////////////// TOWN BUILDING ACTIONS //////////////////////

    @FXML
    private void addDojoListener() {
        System.out.println("Dojo is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initDojoBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addTheDarkPortalListener() {
        System.out.println("The Dark Portal is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initTheDarkPortalBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addInnListener() {
        System.out.println("Inn is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initInnBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar),getClass());
    }

    @FXML
    private void addGymListener() {
        System.out.println("Gym is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initGymBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
    }

    @FXML
    private void addTownSquareListener() {
        System.out.println("Town Square is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initTownSquareBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addCityHallListener() {
        System.out.println("City Hall is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initCityHallBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addWellspringListener() {
        System.out.println("Wellspring is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initWellspringBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addBarrackListener() {
        System.out.println("Barrack is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initBarrackBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addTheGreatestWallListener() {
        System.out.println("The Greatest Wall is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        initTheGreatestWallBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    ////////////////////// CURRENCY //////////////////////
    @FXML
    Label goldamount;
    @FXML
    Label battlecoinamount;
    @FXML
    Label diamondamount;
    @FXML
    Label levelamount;
    @FXML
    ProgressBar energybar;

    @FXML
    Label informationPanel;

    ////////////////////// CURRENCY ACTIONS //////////////////////
    private void addBattlecoinListener(int gain) {
        StatsManager.updatBattlecoin(battlecoinamount, gain);
    }

    private void addExperienceListener(int gain) {
        StatsManager.updateExperience(levelamount, gain);
    }

    private void addEnergyListener(int change) {
        StatsManager.updateEnergyStatus(energybar, change);
    }

    private void addGoldListener(int change) {
        if (change > 0)
            StatsManager.updateGoldIncrease(goldamount, diamondamount, change);
        else if (change < 0 && energybar.getProgress() < 1)
            StatsManager.updateGoldDecrease(goldamount, diamondamount, change);
    }

    // CURRENCY PANES //

    @FXML
    private void addInformationLabelListener() {
        System.out.println("Information Window is activated");
        ViewManager.initInformationView(stackpanel,mainpanel);
    }

    /// ACTION PANES ///

    private int red = (int) (Math.random() * 256), green = (int) (Math.random() * 256), blue = (int) (Math.random() * 256);

    private boolean isToggleupRed = true, isToggleupGreen = false, isToggleupBlue = true;

    private int dailydojoattempts = 2;

    @FXML
    private void initDojoBattleScene(int x, int y, double width, double height) {
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
                addExperienceListener((((int) (Math.random() * 500 * Integer.parseInt(levelamount.getText()))) + 50) * Integer.parseInt(levelamount.getText()));
                addGoldListener((int) Math.round(200 * Integer.parseInt(levelamount.getText()) * 0.2));
                addEnergyListener(-50);

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

    private int thedarkportalstage = 1;

    private void initTheDarkPortalBattleScene(int x, int y, double width, double height) {
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
        ImageView iveffect = new ImageView(new Image(getClass().getResource("/sample/Resources/slash.gif").toExternalForm()));

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
                        addGoldListener(((int) (Math.random() * 15) + 35) * thedarkportalstage);
                        addExperienceListener(((int) (Math.random() * 15) + 15) * thedarkportalstage);
                        addEnergyListener(-15);
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

    private void initTownSquareBattleScene(int x, int y, double width, double height) {
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

    private void initCityHallBattleScene(int x, int y, double width, double height) {
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

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        stackPane.getChildren().addAll(actionLabel, titleLabel, runnerLabel, actionButton, speedButton, vehicleButton, returnButton);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initWellspringBattleScene(int x, int y, double width, double height) {
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

    private void initBarrackBattleScene(int x, int y, double width, double height) {
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

    private void initTheGreatestWallBattleScene(int x, int y, double width, double height) {
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

}
