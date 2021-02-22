package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.CurrencyManagement.CurrencyManager;
import sample.UtilityManagement.UtilityManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

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

        //color randomizer
        dojo.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256))));
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

        if(energybar.getProgress()*100>=25) {
            addExperienceListener((((int) (Math.random() * 5)) + 50) * Integer.parseInt(levelamount.getText()));
            addEnergyListener(-20);
        }
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

}
