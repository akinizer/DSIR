package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.CurrencyManagement.CurrencyManager;
import sample.UtilityManagement.UtilityManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    ////////////////////// CONTAINERS //////////////////////
    @FXML private StackPane stackPane;

    ////////////////////// LOCATIONS //////////////////////
    @FXML private Tab towntab;
    @FXML private Tab dungeontab;
    @FXML private Tab colessiumntab;
    @FXML private Tab storetab;
    @FXML private Tab neighborhoodtab;

    ////////////////////// TOWN //////////////////////
    @FXML private Label inn;
    @FXML private Label thedarkforest;
    @FXML private Label cityhall;
    @FXML private Label dojo;
    @FXML private Label gym;
    @FXML private Label thegreatestwall;
    @FXML private Label townsquare;
    @FXML private Label wellspring;
    @FXML private Label barracks;

    CurrencyManager currencyManager;

    public Controller(){
        currencyManager=new CurrencyManager();
    }

    public List<Tab> getGameTabs(){
        return Arrays.asList(towntab,dungeontab,colessiumntab,storetab,neighborhoodtab);
    }

    public List<Label> getTownBuildings(){
        AnchorPane townAnchorPane = (AnchorPane)towntab.getContent();

        List<Label> resultlist=new ArrayList<>();
        for (Node node : townAnchorPane.getChildren()) {
            resultlist.add((Label) node);
        }

        return resultlist;
    }

    public Label getTownBuilding(String name){
       for (Label label:getTownBuildings()) {
            if(label.getText().toLowerCase().equals(name.toLowerCase()))
                return label;
       }
       return null;
    }

    public void setTownBuildingName(String name, String newname){
        getTownBuilding(name).setText(newname);
    }

    ////////////////////// TOWN BUILDING ACTIONS //////////////////////

    @FXML
    private void addDojoListener(){
        System.out.println("Dojo is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initDojoBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addTheDarkPortalListener(){
        System.out.println("The Dark Portal is clicked");

        if(energybar.getProgress()*100>=15) {
            addGoldListener(((int) (Math.random() * 15) + 35) * Integer.parseInt(levelamount.getText()));
            addExperienceListener(((int) (Math.random() * 15) + 15) * Integer.parseInt(levelamount.getText()));
            addEnergyListener(-15);
        }
    }

    @FXML
    private void addInnListener(){
        System.out.println("Inn is clicked");

        int costGold=120;
        int currencyasgold=Integer.parseInt(goldamount.getText())+Integer.parseInt(diamondamount.getText())*100;
        if(currencyasgold>=costGold) {
            addGoldListener(-costGold);
            energybar.setProgress(1);
        }
    }

    @FXML
    private void addGymListener(){
        System.out.println("Gym is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initGymBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addTownSquareListener(){
        System.out.println("Town Square is clicked");
    }

    @FXML
    private void addCityHallListener(){
        System.out.println("City Hall is clicked");
    }

    @FXML
    private void addWellspringListener(){
        System.out.println("Wellspring is clicked");
    }

    @FXML
    private void addBarrackListener(){
        System.out.println("Barrack is clicked");
    }

    @FXML
    private void addTheGreatestWallListener(){
        System.out.println("The Greatest Wall is clicked");
    }

    ////////////////////// CURRENCY //////////////////////
    @FXML Label goldamount;
    @FXML Label battlecoinamount;
    @FXML Label diamondamount;
    @FXML Label levelamount;
    @FXML ProgressBar energybar;

    ////////////////////// CURRENCY ACTIONS //////////////////////
    @FXML
    private void addGoldListener(int change){
        if(change>0)
            currencyManager.updateGoldIncrease(goldamount,diamondamount,change);
        else if(change<0 && energybar.getProgress()<1)
            currencyManager.updateGoldDecrease(goldamount,diamondamount,change);
    }
    @FXML
    private void addBattlecoinListener(int gain){
        currencyManager.updatBattlecoin(battlecoinamount,gain);
    }
    @FXML
    private void addExperienceListener(int gain){
        currencyManager.updateExperience(levelamount,gain);
    }
    @FXML void addEnergyListener(int change){
        currencyManager.updateEnergyStatus(energybar,change);
    }

    /// ACTION PANES ///
    @FXML void initGymBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Battle Scene is Activated");

        //Action Label settings
        Label actionLabel = new Label();

        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.SKYBLUE));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        Button actionButton = new Button("battle time!");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            if(energybar.getProgress()*100>=25) {
                addExperienceListener((((int) (Math.random() * 5)) + 50) * Integer.parseInt(levelamount.getText()));
                addEnergyListener(-20);
                addGoldListener((int)Math.round(200*Integer.parseInt(levelamount.getText())*1.2));
            }

            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(actionLabel);
        stackPane.getChildren().add(actionButton);

        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    @FXML void initDojoBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Battle Scene is Activated");

        //Action Label settings
        Label actionLabel = new Label();

        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.SKYBLUE));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        Button actionButton = new Button("battle time!");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            //color randomizer
            dojo.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256))));

            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(actionLabel);
        stackPane.getChildren().add(actionButton);

        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

}
