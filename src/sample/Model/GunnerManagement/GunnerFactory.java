package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

import java.util.ArrayList;
import java.util.List;

public class GunnerFactory {
    private StackPane stackPane;
    private double width, height;
    private TabPane maintab;
    private Runner runnerLabel;

    private List<Gunner> hangar = new ArrayList<>();

    public GunnerFactory(StackPane stackPane, double width, double height, TabPane maintab, Runner runner) {
        this.stackPane = stackPane;
        this.width = width;
        this.height = height;
        this.maintab = maintab;
        this.runnerLabel = runner;
    }

    public void produce(Gunner.GunnerFireType gtype, int projectileSpeed, int cooldown) {

        Gunner gunnerlabel = new Gunner(stackPane, maintab, width, height);
        hangar.add(gunnerlabel);
        stackPane.getChildren().add(gunnerlabel);

        if (runnerLabel.isVisible())
            gunnerlabel.fire(runnerLabel, projectileSpeed, gtype);

        Timeline gunfire = new Timeline();
        KeyFrame gunkeyframe = new KeyFrame(Duration.millis(cooldown),event -> {
            if (runnerLabel.isVisible()) {
                int overheat = gunnerlabel.getOverheatCount();

                //BurstFire Action for Guided/GuidedBoss Projectile
                if(gtype==Gunner.GunnerFireType.GUIDED || gtype==Gunner.GunnerFireType.GUIDEDBOSS) {
                    //Burst of Projectiles are 3 for each 8 fire-duration
                    if(overheat%8<=2)
                        gunnerlabel.fire(runnerLabel, projectileSpeed, gtype);
                }

                //Action for Non-Guided Types
                else gunnerlabel.fire(runnerLabel, projectileSpeed, gtype);

            }
        });

        gunfire.getKeyFrames().add(gunkeyframe);
        gunfire.setCycleCount(Timeline.INDEFINITE);
        gunfire.play();
    }

    public void bulkproduce(int countStrafe, int countHommer, int countGuided, int countG_Homing, boolean enableBoss) {
        if(!enableBoss) {
            for (int i = 0; i < countStrafe; i++) {
                produce(Gunner.GunnerFireType.STRAIGHT, 10, 50);
            }
            for (int i = 0; i < countHommer; i++) {
                produce(Gunner.GunnerFireType.HOMING, 25, 5000);
            }
            for (int i = 0; i < countGuided; i++) {
                produce(Gunner.GunnerFireType.GUIDED, 10, 500);
            }
            for (int i = 0; i < countG_Homing; i++) {
                produce(Gunner.GunnerFireType.G_HOMING, 10, 50);
            }
        }
        else {
            for (int i = 0; i < countStrafe; i++) {
                produce(Gunner.GunnerFireType.STRAIGHTBOSS, 10, 50);
            }
            for (int i = 0; i < countHommer; i++) {
                produce(Gunner.GunnerFireType.HOMINGBOSS, 25, 5000);
            }
            for (int i = 0; i < countHommer; i++) {
                produce(Gunner.GunnerFireType.GUIDEDBOSS, 10, 500);
            }
            for (int i = 0; i < countG_Homing; i++) {
                produce(Gunner.GunnerFireType.G_HOMINGBOSS, 5, 50);
            }
        }
    }

    private int counter=0;
    public void waveproduce(int countStrafe, int countHommer, int phaseduration, int upperlimit) {
        Timeline timer = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(phaseduration),event -> {
            if(counter++ <upperlimit) {
                for (int i = 0; i < countStrafe; i++) {
                    produce(Gunner.GunnerFireType.STRAIGHT, 10, 50);
                }
                for (int i = 0; i < countHommer; i++) {
                    produce(Gunner.GunnerFireType.HOMING, 25, 50);
                }
            }
            else timer.stop();
        });
        timer.getKeyFrames().add(keyFrame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
}
