package sample.SuperiorManagement.RunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.SuperiorManagement.UtilityManagement.UtilityManager;

public class Projectile extends Label {
    //attributes
    private double x,y;
    private double speed;
    private double cooldown;



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
    private double counterCycle;
    void fire(int direction,String projectileType){
        switch ( projectileType ) {
            case "minigun": {
                setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.RED));
                speed = 50;
                cooldown = 0.1;

                Timeline timer = new Timeline();
                timer.getKeyFrames().add(new KeyFrame(Duration.millis(100 / speed), event -> {
                    //remove the projectile out of City Hall
                    StackPane parent = ((StackPane)getParent());
                    boolean validIntervalX=(getTranslateX()>0 && getTranslateX()<parent.getWidth());
                    boolean validIntervalY=(getTranslateY()>0 && getTranslateY()<parent.getHeight());

                    if(!(validIntervalX && validIntervalY)){
                        timer.stop();
                        parent.getChildren().remove(this);
                        return;
                    }
                    //if projectile is inside battle scene, execute fire projectile operation
                    System.out.println("open fire!!!");//if bullet is fired it gives a message, otherwise the rest will be ignored by return case

                    switch ( direction ) {
                        case 1:
                            setTranslateY(getTranslateY() - getMinHeight());
                            setRotate(0);
                            setVisible(true);
                            break;
                        case 2:
                            setTranslateX(getTranslateX() + 5);
                            setRotate(90);
                            if (!adjust) {
                                setTranslateY(getTranslateY() - getMinHeight());
                                System.out.println((getTranslateY()));
                                System.out.println(y);
                                adjust = true;
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
                            if (!adjust) {
                                setTranslateY(getTranslateY() - 5);
                                adjust = true;
                            }
                            setVisible(true);
                            break;
                    }
                }));

                timer.setCycleCount(Timeline.INDEFINITE);
                timer.play();

                break;
            }
            case "missile": {
                setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.BLACK));
                speed = 1;
                cooldown = 4;

                counterCycle=0;
                Timeline timer = new Timeline();
                timer.getKeyFrames().add(new KeyFrame(Duration.millis(100 / speed), event -> {
                    //remove the projectile out of City Hall
                    StackPane parent = ((StackPane)getParent());
                    boolean validIntervalX=(getTranslateX()>0 && getTranslateX()<parent.getWidth());
                    boolean validIntervalY=(getTranslateY()>0 && getTranslateY()<parent.getHeight());

                    if(!(validIntervalX && validIntervalY)){
                        timer.stop();
                        parent.getChildren().remove(this);
                        return;
                    }
                    //if projectile is inside battle scene, execute fire projectile operation
                    System.out.println("open fire!!!");//if bullet is fired it gives a message, otherwise the rest will be ignored by return case

                    counterCycle++;
                    switch ( direction ) {

                        case 1:
                            setTranslateY(getTranslateY() - counterCycle/2);
                            setRotate(0);
                            setVisible(true);
                            break;
                        case 2:
                            setTranslateX(getTranslateX() + counterCycle/2);
                            setRotate(90);
                            if (!adjust) {
                                setTranslateY(getTranslateY() - 5);
                                System.out.println((getTranslateY()));
                                System.out.println(y);
                                adjust = true;
                            }
                            setVisible(true);
                            break;
                        case 3:
                            setTranslateY(getTranslateY() + counterCycle/2);
                            setRotate(180);
                            setVisible(true);
                            break;
                        case 4:
                            setTranslateX(getTranslateX() - counterCycle/2);
                            setRotate(270);
                            if (!adjust) {
                                setTranslateY(getTranslateY() - 5);
                                adjust = true;
                            }
                            setVisible(true);
                            break;
                    }
                }));
                timer.setCycleCount(Timeline.INDEFINITE);
                timer.play();
                break;
            }
            case "sniper": {
                setStyle("-fx-background-color: " + UtilityManager.getHexColor(Color.BLUE));
                speed = 100;
                cooldown = 5;

                Timeline timer = new Timeline();
                timer.getKeyFrames().add(new KeyFrame(Duration.millis(100 / speed), event -> {
                    //remove the projectile out of City Hall
                    StackPane parent = ((StackPane)getParent());
                    boolean validIntervalX=(getTranslateX()>0 && getTranslateX()<parent.getWidth());
                    boolean validIntervalY=(getTranslateY()>0 && getTranslateY()<parent.getHeight());

                    if(!(validIntervalX && validIntervalY)){
                        timer.stop();
                        parent.getChildren().remove(this);
                        return;
                    }
                    //if projectile is inside battle scene, execute fire projectile operation
                    System.out.println("open fire!!!");//if bullet is fired it gives a message, otherwise the rest will be ignored by return case

                    switch ( direction ) {
                        case 1:
                            setTranslateY(getTranslateY() - getMinHeight());
                            setRotate(0);
                            setVisible(true);
                            break;
                        case 2:
                            setTranslateX(getTranslateX() + 5);
                            setRotate(90);
                            if (!adjust) {
                                setTranslateY(getTranslateY() - getMinHeight());
                                System.out.println((getTranslateY()));
                                System.out.println(y);
                                adjust = true;
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
                            if (!adjust) {
                                setTranslateY(getTranslateY() - 5);
                                adjust = true;
                            }
                            setVisible(true);
                            break;
                    }
                }));
                timer.setCycleCount(Timeline.INDEFINITE);
                timer.play();
                break;
            }
        }
    }

    //getters and setters
    public double getSpeed(){
        return this.speed;
    }
    public double getCooldown(){
        return this.cooldown;
    }
}
