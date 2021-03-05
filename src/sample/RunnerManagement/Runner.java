package sample.RunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.UtilityManagement.UtilityManager;

import java.util.Timer;

public class Runner extends Label {

    //Components
    private TabPane maintab;
    private Tab towntab;
    StackPane stackPane;
    private boolean isRunnerActive=false;
    private double width, height;
    private Button actionButton, speedButton;
    private ImageView iv;

    //Attributes
    private int speed;
    private int distancecounter;
    private double firerate, cooldown;

    //Constructor
    public Runner(TabPane maintab, double width, double height, Button actionButton, Button speedButton, Tab towntab, StackPane stackPane){
        //component settings
        this.maintab=maintab;
        this.width=width/2 -5;
        this.height=height/2 -5;
        this.actionButton=actionButton;
        this.speedButton=speedButton;
        this.towntab=towntab;
        this.stackPane=stackPane;

        //attribute initial values
        speed=1;
        distancecounter=0;
        firerate=1;
        cooldown=0;

        //settings initialization
        setPresets();
        setMouseListener();
        setKeyListener();
    }

    //settings
    private void setPresets(){
        setCarView();
        setGraphic(iv);
        setTranslateX(width);
        setTranslateY(height);
        setPrefWidth(25);
        setPrefHeight(25);
        setContentDisplay(ContentDisplay.CENTER);
        setTooltip();
        setVisible(false);
    }

    //mouse events: start button, reset button, tooltip
    private void setMouseListener(){
        actionButton.setOnMouseReleased(mouseEvent -> {
            setTranslateX(width);
            setTranslateY(height);
            setVisible(true);

            maintab.requestFocus();
            iv.setRotate(0);

            actionButton.setText("restart!");
            speedButton.setDisable(false);
            isRunnerActive=true;
            distancecounter=0;

            setTooltip();

            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(10), event -> {}));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        });
    }

    //key events: movement, firing projectiles
    private int direction=0;
    private void setKeyListener(){
        maintab.setOnKeyPressed(keyEvent -> {
            if(towntab.isSelected()) {
                //TODO: this setting disables cross movement,
                // to ENABLE cross movement set all cases if and assign cross movement images with additional if cases

                //movement and movement speed, direction, distance
                switch (keyEvent.getText().toLowerCase()) {
                    case "d":
                        iv.setRotate(90);
                        setTranslateX(getTranslateX() + 2*speed);
                        distancecounter+=2*speed;
                        direction=2;
                        break;
                    case "a":
                        iv.setRotate(-90);
                        setTranslateX(getTranslateX() - 2*speed);
                        distancecounter+=2*speed;
                        direction=4;
                        break;
                    case "s":
                        iv.setRotate(180);
                        setTranslateY(getTranslateY() + 2*speed);
                        distancecounter+=2*speed;
                        direction=3;
                        break;
                    case "w":
                        iv.setRotate(0);
                        setTranslateY(getTranslateY() - 2*speed);
                        distancecounter+=2*speed;
                        direction=1;
                        break;

                    default:
                        System.out.println("Key pressed: " + keyEvent.getText().toLowerCase());
                }

                //cooldown validation
                if(isCooldownActive()) return;

                //projectile generation and firing
                if(keyEvent.getText().toLowerCase().equals("f")){
                    //projectile
                    Projectile projectile = new Projectile(getTranslateX()+getWidth()/2.5,getTranslateY()+getHeight()/2.5);
                    stackPane.getChildren().add(projectile);
                    projectile.fire(direction,"minigun");

                    //get firerate and cooldown values from the projectile
                    firerate=projectile.getSpeed();
                    cooldown=projectile.getCooldown();
                    //cooldown on fire
                    initiateCooldown();
                }
            }
            //tooltip on Runner to report stat values
            setTooltip();
        });
    }

    //Tooltip Message
    private void setTooltip(){
        setTooltip(new Tooltip("Position: ("+getTranslateX()+":"+getTranslateY()+")"
                                +"\nDistance: "+distancecounter+ " px"
                                +"\nSpeed: "+speed
                                +"\nFirerate: "+firerate
        ));
    }
    //Runner View Interface
    private void setCarView(){
        iv=new ImageView(new Image(getClass().getResource("/sample/Resources/car-up.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);
    }

    //Runner Movement Speed Update
    public void setSpeed(int speed){
        this.speed=speed;
    }

    //Cooldown Initializer and Validator
    private long starttime=-1;
    private void initiateCooldown(){
        starttime=System.currentTimeMillis();
    }
    private boolean isCooldownActive(){
        long elapsed=(System.currentTimeMillis() - starttime)/1000;
        return starttime!=-1 && elapsed < (long)cooldown;
    }

}
