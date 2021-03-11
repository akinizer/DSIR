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
        ViewManager.initCityHallBattleScene(anchorPane,towntab,maintab,Arrays.asList(goldamount,diamondamount,energybar,levelamount));
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


}
