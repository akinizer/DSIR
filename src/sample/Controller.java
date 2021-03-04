package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.RunnerManagement.Runner;
import sample.StatsManagement.StatsManager;
import sample.UtilityManagement.UtilityManager;
import javafx.scene.image.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    ////////////////////// CONTAINERS //////////////////////
    @FXML private StackPane stackpanel;
    @FXML private Pane mainpanel;
    @FXML private TabPane maintab;

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
        System.out.println("The Dark Portal is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();
        initTheDarkPortalBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addInnListener(){
        System.out.println("Inn is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();
        initInnBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
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

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initTownSquareBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addCityHallListener(){
        System.out.println("City Hall is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initCityHallBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addWellspringListener(){
        System.out.println("Wellspring is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initWellspringBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addBarrackListener(){
        System.out.println("Barrack is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initBarrackBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
    }

    @FXML
    private void addTheGreatestWallListener(){
        System.out.println("The Greatest Wall is clicked");

        AnchorPane anchorPane = (AnchorPane)towntab.getContent();;
        initTheGreatestWallBattleScene(0,0,anchorPane.getWidth(),anchorPane.getHeight());
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
        System.out.println("Information Window is activated");
        initInformationWindow(mainpanel.getLayoutX(),mainpanel.getLayoutY(),mainpanel.getWidth(),mainpanel.getHeight());
    }

    private void initInformationWindow(double x, double y, double width, double height){
        System.out.println("Character Window is shown");
        stackpanel.requestFocus();

        //Action Label settings
        Label actionLabel = new Label("\nAtk:" +currencyManager.getAtk()+
                                        "\nHP:" +currencyManager.getHp()+
                                        "\nLevel:" +currencyManager.getLevel()+
                                        "\nClass:" +currencyManager.getClasstype()+
                                        "\n\n");

        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setAlignment(Pos.CENTER);
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.GREENYELLOW));

        //Name textfield settings
        TextField namefield = new TextField();
        namefield.setText(currencyManager.getName());
        namefield.setMaxWidth(150);
        namefield.setTranslateX(width/2 - namefield.getMaxWidth()/2);
        namefield.setTranslateY(height/3);
        namefield.setAlignment(Pos.CENTER);

        //select all text on click
        namefield.setOnMouseClicked(actionEvent->namefield.selectAll());

        //namefield typing operations
        namefield.setOnKeyReleased(actionEvent -> {
            //character limit is 10
            String curname = currencyManager.getName();
            if(namefield.getText().length()>10) {
                namefield.setText(namefield.getText().substring(0, 10));
            }

            //list of permitted characters
            ArrayList<String> includedChars=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","p","r","s","t","u","v",
                    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Z",
                    "w","x","z","1","2","3","4","5","6","7","8","9","0","_"));

            //permission condition
            boolean isIncluded=includedChars.indexOf(actionEvent.getText())!=-1;

            //check whether a new character is allowed
            if(!isIncluded){
                namefield.setText(namefield.getText().replace(actionEvent.getText(),""));
            }

            //save the name input to the system if it has changed
            if(actionEvent.getCode().equals(KeyCode.ENTER)) {
                if(!namefield.getText().equals(curname)) {
                    curname=namefield.getText();
                    currencyManager.setName(namefield.getText());
                    System.out.println(currencyManager.getName());
                }
            }

            //set caret cursor position to last
            namefield.positionCaret(Math.min(namefield.getText().length()+1,10));
        });


        //Save instance of the tab
        Node contentSaved = stackpanel.getChildren().get(0);

        Button actionButton = new Button("Return");
        actionButton.setLayoutX(0);
        actionButton.setLayoutY(0);

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnKeyPressed(mouseEvent -> {
            actionLabel.setVisible(false);
            stackpanel.getChildren().removeAll(actionLabel,actionButton);
            stackpanel.getChildren().add(contentSaved);
            stackpanel.setAlignment(Pos.CENTER);
        });

        //Remove focus from namefield
        actionLabel.setOnMouseClicked(mouseEvent -> {
            actionLabel.requestFocus();
        });

        //Initiate Battle Scene
        stackpanel.getChildren().remove(contentSaved);
        stackpanel.getChildren().addAll(actionLabel,namefield,actionButton);
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
    @FXML
    private void addEnergyListener(int change){
        currencyManager.updateEnergyStatus(energybar,change);
    }

    /// ACTION PANES ///
    private void initInnBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Inn Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("INN");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.SKYBLUE));
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
        ImageView ivnap = new ImageView(new Image(getClass().getResource("/sample/Resources/couch.png").toExternalForm()));
        ivnap.setFitHeight(100);
        ivnap.setPreserveRatio(true);

        Label labelnap = new Label("",ivnap);
        labelnap.setPrefWidth(100);
        labelnap.setPrefHeight(100);
        labelnap.setTranslateX(width/2 - labelnap.getPrefWidth()/2);
        labelnap.setTranslateY(height/2 -ivnap.getFitHeight()/2);
        labelnap.setAlignment(Pos.CENTER);
        labelnap.setOpacity(0.5);
        labelnap.setTooltip(new Tooltip("--Nap--\nCost: 80 G\nEnergy Refill: 66%"));

        //sleep
        ImageView ivsleep = new ImageView(new Image(getClass().getResource("/sample/Resources/bed.png").toExternalForm()));
        ivsleep.setFitHeight(100);
        ivsleep.setPreserveRatio(true);

        Label labelsleep = new Label("",ivsleep);
        labelsleep.setPrefWidth(100);
        labelsleep.setPrefHeight(100);
        labelsleep.setTranslateX(width/2 - labelnap.getPrefWidth()/2 + labelsleep.getPrefWidth() + 50);
        labelsleep.setTranslateY(height/2 -ivsleep.getFitHeight()/2);
        labelsleep.setAlignment(Pos.CENTER);
        labelsleep.setOpacity(0.5);
        labelsleep.setTooltip(new Tooltip("--Sleep--\nCost: 120 G\nEnergy Refill: 100%"));

        ///break
        ImageView ivbreak = new ImageView(new Image(getClass().getResource("/sample/Resources/armchair.png").toExternalForm()));
        ivbreak.setFitHeight(100);
        ivbreak.setPreserveRatio(true);

        Label labelbreak = new Label("",ivbreak);
        labelbreak.setPrefWidth(100);
        labelbreak.setPrefHeight(100);
        labelbreak.setTranslateX(width/2 - labelnap.getPrefWidth()/2 - labelbreak.getPrefWidth() - 50);
        labelbreak.setTranslateY(height/2 - ivbreak.getFitHeight()/2);
        labelbreak.setAlignment(Pos.CENTER);
        labelbreak.setOpacity(0.5);
        labelbreak.setTooltip(new Tooltip("--Break--\nCost: 40 G\nEnergy Refill: 33%"));

        //Load saved instance of tab on leaving Battle Scene
        labelnap.setOnMouseReleased(mouseEvent -> {
            int costGold=40;
            int currencyasgold=Integer.parseInt(goldamount.getText())+Integer.parseInt(diamondamount.getText())*100;
            if(currencyasgold>=costGold) {
                addGoldListener(-costGold);
                double progress=energybar.getProgress()+0.66;
                energybar.setProgress(Math.min(progress,1));
            }
        });

        labelsleep.setOnMouseReleased(mouseEvent -> {
            int costGold=120;
            int currencyasgold=Integer.parseInt(goldamount.getText())+Integer.parseInt(diamondamount.getText())*100;
            if(currencyasgold>=costGold) {
                addGoldListener(-costGold);
                energybar.setProgress(1);
            }
        });

        labelbreak.setOnMouseReleased(mouseEvent -> {
            int costGold=80;
            int currencyasgold=Integer.parseInt(goldamount.getText())+Integer.parseInt(diamondamount.getText())*100;
            if(currencyasgold>=costGold) {
                addGoldListener(-costGold);
                double progress=energybar.getProgress()+0.33;
                energybar.setProgress(Math.min(progress,1));
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        //hover effects
        labelnap.setOnMouseEntered(mouseEvent -> {
            labelnap.setOpacity(1);
        });
        labelnap.setOnMouseExited(mouseEvent -> {
            labelnap.setOpacity(0.5);
        });
        //
        labelbreak.setOnMouseEntered(mouseEvent -> {
            labelbreak.setOpacity(1);
        });
        labelbreak.setOnMouseExited(mouseEvent -> {
            labelbreak.setOpacity(0.5);
        });
        //
        labelsleep.setOnMouseEntered(mouseEvent -> {
            labelsleep.setOpacity(1);
        });
        labelsleep.setOnMouseExited(mouseEvent -> {
            labelsleep.setOpacity(0.5);
        });


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,labelbreak,labelnap,labelsleep,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initGymBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Gym Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("GYM");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIGHTPINK));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("battle time!");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            if(energybar.getProgress()*100>=25) {
                addExperienceListener((((int) (Math.random() * 5)) + 50) * Integer.parseInt(levelamount.getText()));
                addEnergyListener(-20);
                addGoldListener((int)Math.round(200*Integer.parseInt(levelamount.getText())*1.2));
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private int red=(int)(Math.random()*256), green=(int)(Math.random()*256), blue=(int)(Math.random()*256);

    private boolean isToggleupRed=true, isToggleupGreen=false, isToggleupBlue=true;

    int dailydojoattempts=2;

    @FXML
    private void initDojoBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Dojo Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("DOJO");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();

        //color transition for background of dojo battle scene
        double duration = 10;

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

        //action button presets
        Button actionButton = new Button("attempt: "+dailydojoattempts);
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            if(dailydojoattempts>0 && energybar.getProgress()*100>=50) {
                    //update loots
                    addExperienceListener((((int) (Math.random() * 500*Integer.parseInt(levelamount.getText()))) + 50) * Integer.parseInt(levelamount.getText()));
                    addGoldListener((int)Math.round(200*Integer.parseInt(levelamount.getText())*0.2));
                    addEnergyListener(-50);

                    //update attempts
                    dailydojoattempts--;
                    actionButton.setText("attempt: "+dailydojoattempts);
            }
        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            timer.pause();
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);

        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private int thedarkportalstage=1;

    private void initTheDarkPortalBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("The Dark Portal Window is Activated");

        //Title Label
        Label titleLabel = new Label("THE DARK PORTAL");
        titleLabel.setPrefWidth(200);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        //Background Label
        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIME));
        actionLabel.setTranslateX(x);
        actionLabel.setTranslateY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setAlignment(Pos.CENTER);

        //Effect Label
        ImageView iveffect = new ImageView(new Image(getClass().getResource("/sample/Resources/slash.gif").toExternalForm()));

        Label effectlabel = new Label("",iveffect);
        effectlabel.setPrefWidth(100);
        effectlabel.setPrefHeight(178);
        effectlabel.setTranslateX(width/2 - 9*effectlabel.getPrefWidth()/8);
        effectlabel.setTranslateY(height/2 - effectlabel.getPrefHeight()/2);
        effectlabel.setAlignment(Pos.CENTER);

        effectlabel.setVisible(false);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("Stage Battle"+thedarkportalstage);
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {
            /**Two separate clicks handled at the same time, new click starts a new instance and doesnt overwrite previous click
             System.out.println("Primary button: "+!mouseEvent.isPrimaryButtonDown());
             System.out.println("Secondary button: "+!mouseEvent.isSecondaryButtonDown());
             */

            //One click handler at a time, when another clic happens, previous click is terminated
            if(mouseEvent.getButton()== MouseButton.PRIMARY) {
                //function1: on left click
                System.out.println("Primary button is clicked");

                //Battle
                if(actionButton.getText().startsWith("Stage Battle")) {
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
                }
                else if(actionButton.getText().equals("Next")){
                    //update text for the current stage
                    actionButton.setText("Stage Battle "+ ++thedarkportalstage);
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

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,effectlabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initTownSquareBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Town Square Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("TOWN SQUARE");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIGHTGRAY));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initCityHallBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("City Hall Window is Activated");
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_LEFT);

        //Action Label settings
        Label titleLabel = new Label("CITY HALL");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.YELLOW));
        actionLabel.setTranslateX(x);
        actionLabel.setTranslateY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("start!");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //RUNNER CLASS
        Label runnerLabel = new Runner(maintab,width,height,actionButton,towntab);

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        stackPane.getChildren().addAll(actionLabel,titleLabel,runnerLabel,actionButton,returnButton);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initWellspringBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Wellspring Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("WELLSPRING");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIGHTCORAL));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initBarrackBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("Barrack Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("BARRACK");
        titleLabel.setPrefWidth(100);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIGHTSTEELBLUE));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

    private void initTheGreatestWallBattleScene(int x, int y, double width, double height){
        //Log Message
        System.out.println("The Greatest Wall Window is Activated");

        //Action Label settings
        Label titleLabel = new Label("THE GREATEST WALL");
        titleLabel.setPrefWidth(200);
        titleLabel.setPrefHeight(28);
        titleLabel.setTranslateX(width/2 - titleLabel.getPrefWidth()/2);
        titleLabel.setTranslateY(25);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-border-color: White");

        Label actionLabel = new Label();
        actionLabel.setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.LIGHTCYAN));
        actionLabel.setLayoutX(x);
        actionLabel.setLayoutY(y);
        actionLabel.setPrefWidth(width);
        actionLabel.setPrefHeight(height);
        actionLabel.setContentDisplay(ContentDisplay.CENTER);

        //Save instance of the tab
        Node contentSaved = towntab.getContent();

        //action button presets
        Button actionButton = new Button("no action");
        actionButton.setPrefSize(100,28);

        actionButton.setTranslateX(width/2-actionButton.getPrefWidth()/2);
        actionButton.setTranslateY(height-35);

        Button returnButton = new Button("return");

        //Load saved instance of tab on leaving Battle Scene
        actionButton.setOnMouseReleased(mouseEvent -> {

        });

        returnButton.setOnMouseReleased(mouseEvent -> {
            actionLabel.setVisible(false);
            towntab.setContent(contentSaved);
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(actionLabel,titleLabel,actionButton,returnButton);
        stackPane.setAlignment(Pos.TOP_LEFT);
        //Initiate Battle Scene
        towntab.setContent(stackPane);
    }

}
