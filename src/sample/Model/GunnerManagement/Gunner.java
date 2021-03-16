package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.css.Size;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

import java.util.*;


public class Gunner extends Label implements IGunner{
    public enum GunnerFireType{
        STRAIGHT, HOMING
    }

    private double width;
    private double height;
    private Projectile projectile;
    private StackPane stackPane;

    private enum Direction{
        UP,DOWN,LEFT,RIGHT
    }

    public Gunner(StackPane stackPane, TabPane maintab, double width, double height) {
        this.stackPane=stackPane;
        this.width=maintab.getWidth();
        this.height=maintab.getHeight();

        setText("[o]");
        setGraphic(null);
        setVisible(true);

        Random random = new Random();

        setTranslateX(random.nextInt((int)width));
        setTranslateY(new Random().nextInt((int)height));

        setMotionListener();
    }

    private static int count=0;
    private static Direction currentDirection;

    @Override
    public void setMotionListener() {
        Timeline timer = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(25),event -> {
            if(count++ %200 == 0 || !isInBoundary()){
                currentDirection=getRandomDirection();
            }
            getDireAction(currentDirection);
        });
        timer.getKeyFrames().add(keyFrame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private boolean isInBoundary(){
        return 0<getTranslateX() && getTranslateX()<width && 0<getTranslateY() && getTranslateY()<height;
    }

    private void getDireAction(Direction dire){
        switch ( dire ){
            case UP:
                    setTranslateY(getTranslateY()-1);
                break;
            case DOWN:
                    setTranslateY(getTranslateY()+1);
                break;
            case LEFT:
                    setTranslateX(getTranslateX()-1);
                break;
            case RIGHT:
                    setTranslateX(getTranslateX()+1);
                break;
        }
    }
    private Direction getRandomDirection(){
        List<Direction> directionlist= List.of(Direction.values());
        Random random = new Random();
        return directionlist.get(random.nextInt(directionlist.size()));
    }

    public void fire(Runner runner,int speed,GunnerFireType gunnerFireType){
        projectile = new Projectile();
        stackPane.getChildren().add(projectile);

        if(gunnerFireType==GunnerFireType.HOMING)
            projectile.fireHoming(runner,this,speed);
        else if(gunnerFireType==GunnerFireType.STRAIGHT)
            projectile.fire(runner,this,speed);
    }
}
