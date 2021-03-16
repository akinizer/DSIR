package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

public class GunnerFactory {
    private StackPane stackPane;
    private double width,height;
    private TabPane maintab;
    private Runner runnerLabel;

    public GunnerFactory(StackPane stackPane, double width, double height, TabPane maintab, Runner runner) {
        this.stackPane = stackPane;
        this.width = width;
        this.height = height;
        this.maintab = maintab;
        this.runnerLabel = runner;
    }

    public void produce(){
        int speed=10;
        int firerate=50;

        Gunner gunnerlabel= new Gunner(stackPane,maintab,width,height);
        stackPane.getChildren().add(gunnerlabel);

        Gunner.GunnerFireType gtype=Gunner.GunnerFireType.STRAIGHT;

        Timeline gunfire = new Timeline();
        KeyFrame gunkeyframe = new KeyFrame(Duration.millis(firerate), event -> {
            if(runnerLabel.isVisible())
                gunnerlabel.fire(runnerLabel,speed,gtype);
        });
        gunfire.getKeyFrames().add(gunkeyframe);
        gunfire.setCycleCount(Timeline.INDEFINITE);
        gunfire.play();
    }

    public void bulkproduce(int count){
        for(int i=0;i<count;i++){
            produce();
        }
    }
}
