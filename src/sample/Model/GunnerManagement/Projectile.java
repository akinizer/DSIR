package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

class Projectile {
    private StackPane stackPane;
    private Label projectilelabel;

    Projectile(StackPane stackPane){
        this.stackPane=stackPane;
    }

    void fire(Runner runner,Gunner gunner){
        projectilelabel=new Label();



        projectilelabel.setTranslateX(gunner.getTranslateX());
        projectilelabel.setTranslateY(gunner.getTranslateY());
        projectilelabel.setText("O");

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50),event -> {
             double sourcex=gunner.getTranslateX();
             double sourcey=gunner.getTranslateY();

            double targetx=runner.getTranslateX();
            double targety=runner.getTranslateY();

            if(projectilelabel.getTranslateX()==targetx && projectilelabel.getTranslateY()==targety) {
                stackPane.getChildren().remove(projectilelabel);
            }

            projectilelabel.setTranslateX(projectilelabel.getTranslateX()+getSlopeSign(sourcex,targetx));
            projectilelabel.setTranslateY(projectilelabel.getTranslateY()+getSlope(sourcex,sourcey,targetx,targety)*getSlopeSign(sourcex,targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        stackPane.getChildren().add(projectilelabel);
    }

    private double getSlope(double sourcex,double sourcey,double targetx,double targety){
        return (targety-sourcey)/(targetx-sourcex);
    }

    private int getSlopeSign(double sourcex,double targetx){
        if(sourcex>targetx) return -1;
        else return 1;
    }

}
