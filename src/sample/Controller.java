package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.StatsManagement.StatsManager;
import sample.UtilityManagement.UtilityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    ////////////////////// CONTAINERS //////////////////////
    @FXML private StackPane stackpanel;
    @FXML private Pane mainpanel;

    ////////////////////// LOCATIONS //////////////////////
    @FXML private Tab towntab;
    @FXML private Tab dungeontab;
    @FXML private Tab colessiumntab;
    @FXML private Tab storetab;

    ////////////////////// TOWN //////////////////////
    @FXML private Label inn;
    @FXML private Label thedarkportal;
    @FXML private Label cityhall;
    @FXML private Label dojo;
    @FXML private Label gym;
    @FXML private Label thegreatestwall;
    @FXML private Label townsquare;
    @FXML private Label wellspring;
    @FXML private Label barracks;

    private StatsManager currencyManager;

    public Controller(){
        currencyManager=new StatsManager();
    }
    public void updateCharacterInfo(String name, String classname, int atk, int hp){
        if(!name.isEmpty())
            currencyManager.setName(name);

        if(!classname.isEmpty())
            currencyManager.setClasstype(classname);

        currencyManager.setLevel(0);

        if(atk>0 && hp>0) {
            currencyManager.setAtk(atk);
            currencyManager.setHp(hp);
        }
    }
    public void updateCharacterInfo(String name, String classname, int atk, int hp, int level){
        if(!name.isEmpty())
            currencyManager.setName(name);

        if(!classname.isEmpty())
            currencyManager.setClasstype(classname);

        if(level>=0)
            currencyManager.setLevel(level);

        if(atk>0 && hp>0) {
            currencyManager.setAtk(atk);
            currencyManager.setHp(hp);
        }
    }

    public List<Tab> getGameTabs(){
        return Arrays.asList(towntab,dungeontab,colessiumntab,storetab);
    }

    public List<AnchorPane> getGameTabContents(){
        return Arrays.asList((AnchorPane)towntab.getContent(),(AnchorPane)dungeontab.getContent(),(AnchorPane)colessiumntab.getContent(),(AnchorPane)storetab.getContent());
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

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();
        initDojoBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addTheDarkPortalListener(){
        thedarkportal.setOnMouseReleased(mouseEvent -> {
            /**Two separate clicks handled at the same time, new click starts a new instance and doesnt overwrite previous click
            System.out.println("Primary button: "+!mouseEvent.isPrimaryButtonDown());
            System.out.println("Secondary button: "+!mouseEvent.isSecondaryButtonDown());
            */

            //One click handler at a time, when another clic happens, previous click is terminated
            if(mouseEvent.getButton()== MouseButton.PRIMARY) {
                //function1: on left click
                System.out.println("Primary button is clicked");

                if (energybar.getProgress() * 100 >= 15) {
                    addGoldListener(((int) (Math.random() * 15) + 35) * Integer.parseInt(levelamount.getText()));
                    addExperienceListener(((int) (Math.random() * 15) + 15) * Integer.parseInt(levelamount.getText()));
                    addEnergyListener(-15);
                }
            }

            if(mouseEvent.getButton()== MouseButton.SECONDARY) {
                //function2: on right click
                System.out.println("Secondary button is clicked");
            }

            if(mouseEvent.getButton()== MouseButton.MIDDLE) {
                //function3: on wheel click
                System.out.println("Middle button is clicked");
            }



        });
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

    @FXML Label informationPanel;

    ////////////////////// CURRENCY ACTIONS //////////////////////
    @FXML
    private void addInformationLabelListener(){
        System.out.println("hi");
        initInformationWindow(mainpanel.getLayoutX(),mainpanel.getLayoutY(),mainpanel.getWidth(),mainpanel.getHeight());
    }

    private void initInformationWindow(double x, double y, double width, double height){
        System.out.println("Character Window is shown");

        //Action Label settings
        Label actionLabel = new Label(
                "Name:"+currencyManager.getName()
                        +"\nAtk:" +currencyManager.getAtk()+
                        "\nHP:" +currencyManager.getHp()+
                        "\nLevel:" +currencyManager.getLevel()+
                        "\nClass:" +currencyManager.getClasstype()+
                        "\n\n");

        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.GREENYELLOW));

        //Save instance of the tab
        Node contentSaved = stackpanel.getChildren().get(0);

        Button actionButton = new Button("Return");
        actionButton.setLayoutX(0);
        actionButton.setLayoutY(0);

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            stackpanel.getChildren().removeAll(actionLabel,actionButton);
            stackpanel.getChildren().add(contentSaved);
            stackpanel.setAlignment(Pos.CENTER);
        });

        //Initiate Battle Scene
        stackpanel.getChildren().remove(contentSaved);
        stackpanel.getChildren().addAll(actionLabel,actionButton);
        stackpanel.setAlignment(Pos.TOP_LEFT);
    }

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

    private double duration=10;
    private int red=(int)(Math.random()*256), green=(int)(Math.random()*256), blue=(int)(Math.random()*256);

    private boolean isToggleupRed=true, isToggleupGreen=false, isToggleupBlue=true;

    @FXML void initDojoBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Battle Scene is Activated");

        //Action Label settings
        Label actionLabel = new Label();

        //color transition for background of dojo battle scene
        Timeline timer = new Timeline(
        new KeyFrame(Duration.millis(duration), event -> {
            //transition of red
            if(red>=0 && red<256){
               if(red==0)
                   isToggleupRed=true;
               else if(red==255)
                   isToggleupRed=false;

               if (isToggleupRed)
                   red++;
               else {
                   red--;

               }
            }
            //transition of green
            if(green>=0 && green<256){
                if(green==0)
                    isToggleupGreen=true;
                else if(green==255)
                    isToggleupGreen=false;

                if (isToggleupGreen)
                    green++;
                else {
                    green--;
                }
            }
            //transition of blue
            if(blue>=0 && blue<256){
                if(blue==0)
                    isToggleupBlue=true;
                else if(blue==255)
                    isToggleupBlue=false;

                if (isToggleupBlue)
                    blue++;
                else {
                    blue--;
                }
            }

            actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.rgb(red,green,blue)));
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

        Button actionButton = new Button("battle time!");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            timer.pause();
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(actionLabel);
        stackPane.getChildren().add(actionButton);

        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

}
