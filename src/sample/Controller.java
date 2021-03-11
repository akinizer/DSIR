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
        ViewManager.initDojoBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
        //initDojoBattleScene(0, 0, anchorPane.getWidth(), anchorPane.getHeight());
    }

    @FXML
    private void addTheDarkPortalListener() {
        System.out.println("The Dark Portal is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initTheDarkPortalBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount),getClass());
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
        ViewManager.initTownSquareBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
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
        ViewManager.initWellspringBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
    }

    @FXML
    private void addBarrackListener() {
        System.out.println("Barrack is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initBarrackBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
    }

    @FXML
    private void addTheGreatestWallListener() {
        System.out.println("The Greatest Wall is clicked");

        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initTheGreatestWallBattleScene(anchorPane,towntab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
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
    // CURRENCY PANES //

    @FXML
    private void addInformationLabelListener() {
        System.out.println("Information Window is activated");
        ViewManager.initInformationView(stackpanel,mainpanel);
    }

    /// ACTION PANES ///
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

}
