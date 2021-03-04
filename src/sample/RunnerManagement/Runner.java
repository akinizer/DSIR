package sample.RunnerManagement;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sample.UtilityManagement.UtilityManager;

public class Runner extends Label {

    private TabPane maintab;
    private Tab towntab;
    private boolean isRunnerActive=false;
    private double width, height;
    private Button actionButton;
    private ImageView iv;

    public Runner(TabPane maintab, double width, double height, Button actionButton,Tab towntab){
        this.maintab=maintab;
        this.width=width/2 -5;
        this.height=height/2 -5;
        this.actionButton=actionButton;
        this.towntab=towntab;

        setPresets();
        setMouseListener();
        setKeyListener();
    }

    private void setPresets(){
        //setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.RED) + "; -fx-background-radius: 30px");
        iv=new ImageView(new Image(getClass().getResource("/sample/Resources/car-up.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);

        setGraphic(iv);
        setTranslateX(width);
        setTranslateY(height);
        setPrefWidth(25);
        setPrefHeight(25);
        setContentDisplay(ContentDisplay.CENTER);
        setTooltip(new Tooltip("Position: ("+getTranslateX()+":"+getTranslateY()+")"));
        setVisible(false);
    }
    private void setMouseListener(){
        actionButton.setOnMouseReleased(mouseEvent -> {
            actionButton.setText("restart!");
            setTranslateX(width);
            setTranslateY(height);
            setVisible(true);
            isRunnerActive=true;
            maintab.requestFocus();
        });
    }
    private void setKeyListener(){
        maintab.setOnKeyPressed(keyEvent -> {
            if(towntab.isSelected()) {
                System.out.println("Key pressed: " + keyEvent.getText().toLowerCase());

                //TODO: this setting disables cross movement,
                // to ENABLE cross movement set all cases if and assign cross movement images with additional if cases

                if (keyEvent.getText().toLowerCase().equals("d")) {
                    iv.setRotate(90);
                    setTranslateX(getTranslateX() + 2);
                }
                else if (keyEvent.getText().toLowerCase().equals("a")) {
                    iv.setRotate(-90);
                    setTranslateX(getTranslateX() - 2);
                }
                else if (keyEvent.getText().toLowerCase().equals("s")) {
                    iv.setRotate(180);
                    setTranslateY(getTranslateY() + 2);
                }
                else if (keyEvent.getText().toLowerCase().equals("w")) {
                    iv.setRotate(0);
                    setTranslateY(getTranslateY() - 2);
                }
            }
            setTooltip(new Tooltip("Position: ("+getTranslateX()+":"+getTranslateY()+")"));
        });
    }

}
