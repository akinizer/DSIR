package sample.RunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.UtilityManagement.UtilityManager;

public class Projectile extends Label {
    //attributes
    private double x,y;
    private double speed;

    //constructor
    public Projectile(double x, double y) { //up 1, right 2, down 3, left 4
        this.x=x;
        this.y=y;

        setPresets();
    }

    //settings
    private void setPresets(){
        setTranslateX(x);
        setTranslateY(y);
        setMinWidth(5);
        setMinHeight(5);
        setAlignment(Pos.CENTER);
        setVisible(false);
    }
    private boolean adjust=false;
    void fire(int direction,String projectileType){
        if(projectileType.equals("bullet")) {
            setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.RED));
            speed=100;
            Timeline timer = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                switch ( direction ) {
                    case 0:
                        setVisible(false);
                        break;
                    case 1:
                        setTranslateY(getTranslateY() - 5);
                        setRotate(0);
                        break;
                    case 2:
                        setTranslateX(getTranslateX() + 5);
                        setRotate(90);
                        break;
                    case 3:
                        setTranslateY(getTranslateY() + 5);
                        setRotate(180);
                        break;
                    case 4:
                        setTranslateX(getTranslateX() - 5);
                        setRotate(270);
                        break;
                }
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        }
        else if(projectileType.equals("missile")) {
            setStyle("-fx-background-color: "+ UtilityManager.getHexColor(Color.BLACK));
            speed=1;

            Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                switch ( direction ) {
                    case 1:
                        setTranslateY(getTranslateY() - getMinHeight());
                        setRotate(0);
                        setVisible(true);
                        break;
                    case 2:
                        setTranslateX(getTranslateX() + 5);
                        setRotate(90);
                        if(!adjust){
                            setTranslateY(getTranslateY() - getMinHeight());
                            System.out.println((getTranslateY()));
                            System.out.println(y);
                            adjust=true;
                        }
                        setVisible(true);
                        break;
                    case 3:
                        setTranslateY(getTranslateY() + 5);
                        setRotate(180);
                        setVisible(true);
                        break;
                    case 4:
                        setTranslateX(getTranslateX() - 5);
                        setRotate(270);
                        if(!adjust){
                            setTranslateY(getTranslateY() - 5);
                            adjust=true;
                        }
                        setVisible(true);
                        break;
                }
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        }
    }

    //getters and setters
    public double getSpeed(){
        return this.speed;
    }
}
