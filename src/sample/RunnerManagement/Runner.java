package sample.RunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.UtilityManagement.UtilityManager;

public class Runner extends Label {

    private TabPane maintab;
    private Tab towntab;
    private boolean isRunnerActive=false;
    private double width, height;
    private Button actionButton, speedButton;
    private ImageView iv;

    private int speed;
    private int distancecounter;

    public Runner(TabPane maintab, double width, double height, Button actionButton, Button speedButton, Tab towntab, StackPane stackPane){
        this.maintab=maintab;
        this.width=width/2 -5;
        this.height=height/2 -5;
        this.actionButton=actionButton;
        this.speedButton=speedButton;
        this.towntab=towntab;

        speed=1;
        distancecounter=0;

        setPresets();
        setMouseListener();
        setKeyListener();
    }

    private void setPresets(){
        //setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.RED) + "; -fx-background-radius: 30px");
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
    private void setKeyListener(){
        maintab.setOnKeyPressed(keyEvent -> {
            if(towntab.isSelected()) {
                //TODO: this setting disables cross movement,
                // to ENABLE cross movement set all cases if and assign cross movement images with additional if cases

                switch ( keyEvent.getText().toLowerCase() ) {
                    case "d":
                        iv.setRotate(90);
                        setTranslateX(getTranslateX() + 2*speed);
                        distancecounter+=2*speed;
                        break;
                    case "a":
                        iv.setRotate(-90);
                        setTranslateX(getTranslateX() - 2*speed);
                        distancecounter+=2*speed;
                        break;
                    case "s":
                        iv.setRotate(180);
                        setTranslateY(getTranslateY() + 2*speed);
                        distancecounter+=2*speed;
                        break;
                    case "w":
                        iv.setRotate(0);
                        setTranslateY(getTranslateY() - 2*speed);
                        distancecounter+=2*speed;
                        break;
                    default:
                        System.out.println("Key pressed: " + keyEvent.getText().toLowerCase());
                }
            }
            setTooltip();
        });
    }
    private void setTooltip(){
        setTooltip(new Tooltip("Position: ("+getTranslateX()+":"+getTranslateY()+")"
                +"Distance: "+distancecounter+ " px"));
    }
    private void setCarView(){
        iv=new ImageView(new Image(getClass().getResource("/sample/Resources/car-up.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);
    }

    public void setSpeed(int speed){
        this.speed=speed;
    }

}
