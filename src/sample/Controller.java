package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Model.StatsManagement.StatsManager;
import sample.View.ViewManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    //Constructor
    public Controller(){
        StatsManager.setCharacterBaseStats();
    }

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
    @FXML
    Label settings;

    ////////////////////// TOWN BUILDING ACTIONS //////////////////////

    @FXML
    private void addDojoListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initDojoView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addTheDarkPortalListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initTheDarkPortalView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount), getClass());
    }

    @FXML
    private void addInnListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initInnView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar), getClass());
    }

    @FXML
    private void addGymListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initGymView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addTownSquareListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initTownSquareView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addCityHallListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initCityHallView(anchorPane, towntab, maintab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addWellspringListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initWellspringView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addBarrackListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initBarrackView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    @FXML
    private void addTheGreatestWallListener() {
        AnchorPane anchorPane = (AnchorPane) towntab.getContent();
        ViewManager.initTheGreatestWallView(anchorPane, towntab, Arrays.asList(goldamount, diamondamount, energybar, levelamount));
    }

    ////////////////////// CURRENCY ACTIONS //////////////////////

    // CURRENCY PANES //
    @FXML
    private void addInformationLabelListener() {
        ViewManager.initInformationView(stackpanel, mainpanel);
    }
    @FXML
    private void addSettingsLabelListener(){ViewManager.initSettingsView(stackpanel);}

    ////////////////////// CONTROLLER TEST ACTIONS //////////////////////

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

    //////////////////////// SKY VIEW ////////////////////////
    private boolean isFleetFullscreen=false;

    /// FLEET ///
    @FXML
    public Label fullscreenToggler;
    @FXML
    public Pane fleet_fullscreen;
    @FXML
    public void addFullScreenTogglerListener() throws IOException {
        ViewManager.addFleetWindowTogglerListener(fleet_fullscreen);
    }

    //////////////////////// UNUSED FXML COMPONENTS ////////////////////////
    @FXML public AnchorPane townpane;

    @FXML public AnchorPane collessiumpane;
    @FXML public Label towerofgodbuilding;
    @FXML public Label elvenforestbuilding;
    @FXML public Label lakeofnympsbuilding;
    @FXML public Label ruinsbuilding;
    @FXML public Label dwarvenminesbuilding;

    @FXML public AnchorPane dungeonpane;
    @FXML public Label competitivebuilding;
    @FXML public Label grandbuilding;
    @FXML public Label electivebuilding;
    @FXML public Label scorebillbuilding;

    @FXML public AnchorPane storepane;
    @FXML public TabPane storetabpanel;
    @FXML public GridPane storegridpane5;
    @FXML public GridPane storegridpane;
    @FXML public GridPane storegridpane2;
    @FXML public GridPane storegridpane3;
    @FXML public GridPane storegridpane4;

    @FXML public Label battlecoinlabel;
    @FXML public Label goldlabel;
    @FXML public Label energylabel;
    @FXML public Label diamondlabel;
    @FXML public Label levellabel;

    @FXML public Tab skytab;
    @FXML public Tab fleettab;
    @FXML public Tab stationtab;
    @FXML public Tab warptab;
    @FXML public Tab zone51tab;
    @FXML public Tab mona02tab;
}
