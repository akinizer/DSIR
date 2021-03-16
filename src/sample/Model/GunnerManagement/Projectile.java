package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

class Projectile extends Label{

    void fireHoming(Runner runner,Gunner gunner,int speed){
        setTranslateX(gunner.getTranslateX());
        setTranslateY(gunner.getTranslateY());

        Timeline timeline = new Timeline();
        double sourcex=gunner.getTranslateX();
        double sourcey=gunner.getTranslateY();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100/speed),event -> {

            double targetminx=runner.getTranslateX()-runner.getWidth()/2;
            double targetmaxx=runner.getTranslateX()+runner.getWidth()/2;
            double targetminy=runner.getTranslateY()-runner.getHeight()/2;
            double targetmaxy=runner.getTranslateY()+runner.getHeight()/2;

            //runner.setStyle("-fx-border-color: red;"); //HITBOX OF RUNNER
            StackPane parent = ((StackPane)getParent());
            boolean validIntervalX=(getTranslateX()>0 && getTranslateX()<parent.getWidth());
            boolean validIntervalY=(getTranslateY()>0 && getTranslateY()<parent.getHeight());

            if(!(validIntervalX && validIntervalY)
                    || (targetminx<=this.getTranslateX() && this.getTranslateX()<=targetmaxx && targetminy<=this.getTranslateY() && this.getTranslateY()<=targetmaxy)){
                timeline.stop();
                parent.getChildren().remove(this);
                return;
            }

            double targetx=runner.getTranslateX();
            double targety=runner.getTranslateY();

            setTranslateX(this.getTranslateX()+getSlopeSign(sourcex,targetx));
            setTranslateY(this.getTranslateY()+getSlope(sourcex,sourcey,targetx,targety)*getSlopeSign(sourcex,targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void fire(Runner runner,Gunner gunner,int speed){
        setTranslateX(gunner.getTranslateX());
        setTranslateY(gunner.getTranslateY());

        Timeline timeline = new Timeline();
        double sourcex=gunner.getTranslateX();
        double sourcey=gunner.getTranslateY();

        double targetx=runner.getTranslateX();
        double targety=runner.getTranslateY();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100/speed),event -> {
            //runner.setStyle("-fx-border-color: red;"); //HITBOX OF RUNNER
            StackPane parent = ((StackPane)getParent());
            boolean validIntervalX=(getTranslateX()>0 && getTranslateX()<parent.getWidth());
            boolean validIntervalY=(getTranslateY()>0 && getTranslateY()<parent.getHeight());

            double targetminx=runner.getTranslateX()-runner.getWidth()/2;
            double targetmaxx=runner.getTranslateX()+runner.getWidth()/2;
            double targetminy=runner.getTranslateY()-runner.getHeight()/2;
            double targetmaxy=runner.getTranslateY()+runner.getHeight()/2;

            if(!(validIntervalX && validIntervalY)
                    || (targetminx<=this.getTranslateX() && this.getTranslateX()<=targetmaxx && targetminy<=this.getTranslateY() && this.getTranslateY()<=targetmaxy)){
                timeline.stop();
                parent.getChildren().remove(this);
                return;
            }

            setTranslateX(this.getTranslateX()+getSlopeSign(sourcex,targetx));
            setTranslateY(this.getTranslateY()+getSlope(sourcex,sourcey,targetx,targety)*getSlopeSign(sourcex,targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private double getSlope(double sourcex,double sourcey,double targetx,double targety){
        return (targety-sourcey)/(targetx-sourcex);
    }

    private int getSlopeSign(double sourcex,double targetx){
        if(sourcex>targetx) return -1;
        else return 1;
    }

}
