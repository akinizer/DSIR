package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Label label=new Label("| |");
    private Label filter=new Label();

    public GunnerFactory(StackPane stackPane, double width, double height, TabPane maintab, Runner runner) {
        this.stackPane = stackPane;
        this.width = width;
        this.height = height;
        this.maintab = maintab;
        this.runnerLabel = runner;

        label.setFont(new Font(label.getFont().getName(), 72));
        label.setTranslateX(maintab.getWidth()*0.45);
        label.setTranslateY(maintab.getHeight()/3);

        filter.setPrefWidth(maintab.getWidth());
        filter.setPrefHeight(maintab.getHeight());
        filter.setStyle("-fx-background-color: white;");
        filter.setOpacity(0.5);

        isPaused=false;
    }

    private void produce(Gunner.GunnerFireType gtype, int projectileSpeed, int cooldown) {


        Gunner gunnerlabel = new Gunner(stackPane, maintab, width, height, gtype);
        hangar.add(gunnerlabel);
        stackPane.getChildren().add(gunnerlabel);

        if (runnerLabel.isVisible() && !gunnerlabel.isCease())
            gunnerlabel.fire(runnerLabel, projectileSpeed, gtype);

        Timeline gunfire = new Timeline();
        KeyFrame gunkeyframe = new KeyFrame(Duration.millis(cooldown), event -> {
            if(gunnerlabel.isDisable()) gunfire.pause();
            else gunfire.play();

            if (gunnerlabel.isCease()) {
                gunfire.stop();
                return;
            }

            //check if runner is active and in the range of gunner
            if (runnerLabel.isVisible() && isInRange(runnerLabel, gunnerlabel, gunnerlabel.getRange(gtype))) {
                int overheat = gunnerlabel.getOverheatCount();

                //BurstFire Action for Guided/GuidedBoss Projectile
                if (gtype == Gunner.GunnerFireType.GUIDED || gtype == Gunner.GunnerFireType.GUIDEDBOSS) {
                    //Burst of Projectiles are 3 for each 8 fire-duration
                    if (overheat % 8 <= 2)
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

    public void typeproduce(Gunner.GunnerFireType gft, int count) {
        if (gft == Gunner.GunnerFireType.STRAIGHT) {
            for (int i = 0; i < count; i++) {
                produce(gft, 10, 50);
            }
        } else if (gft == Gunner.GunnerFireType.HOMING) {
            for (int i = 0; i < count; i++) {
                produce(gft, 25, 5000);
            }
        } else if (gft == Gunner.GunnerFireType.GUIDED) {
            for (int i = 0; i < count; i++) {
                produce(gft, 10, 500);
            }
        } else if (gft == Gunner.GunnerFireType.G_HOMING) {
            for (int i = 0; i < count; i++) {
                produce(Gunner.GunnerFireType.G_HOMING, 10, 50);
            }
        } else if (gft == Gunner.GunnerFireType.STRAIGHTBOSS) {
            for (int i = 0; i < count; i++) {
                produce(Gunner.GunnerFireType.STRAIGHTBOSS, 10, 50);
            }
        } else if (gft == Gunner.GunnerFireType.HOMINGBOSS) {
            for (int i = 0; i < count; i++) {
                produce(Gunner.GunnerFireType.HOMINGBOSS, 25, 5000);
            }
        } else if (gft == Gunner.GunnerFireType.GUIDEDBOSS) {
            for (int i = 0; i < count; i++) {
                produce(Gunner.GunnerFireType.GUIDEDBOSS, 10, 500);
            }
        } else if (gft == Gunner.GunnerFireType.G_HOMINGBOSS) {
            for (int i = 0; i < count; i++) {
                produce(Gunner.GunnerFireType.G_HOMINGBOSS, 5, 50);
            }
        }

    }

    public void bulkproduce(int countStrafe, int countHommer, int countGuided, int countG_Homing, boolean enableBoss) {
        if (!enableBoss) {
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
        } else {
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

    private int counter = 0;

    public void waveproduce(int countStrafe, int countHommer, int phaseduration, int upperlimit) {
        Timeline timer = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(phaseduration), event -> {
            if (counter++ < upperlimit) {
                for (int i = 0; i < countStrafe; i++) {
                    produce(Gunner.GunnerFireType.STRAIGHT, 10, 50);
                }
                for (int i = 0; i < countHommer; i++) {
                    produce(Gunner.GunnerFireType.HOMING, 25, 50);
                }
            } else timer.stop();
        });
        timer.getKeyFrames().add(keyFrame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private boolean isInRange(Runner runner, Gunner gunner, double range) {
        if (range == -1) return true;

        double deltax = Math.abs(runner.getTranslateX() - gunner.getTranslateX());
        double deltay = Math.abs(runner.getTranslateY() - gunner.getTranslateY());

        double distance = Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2));

        return distance <= range;

    }

    public void demolishAllGunners() {
        for (Gunner gunner : hangar) {
            gunner.ceaseFire();
        }
        stackPane.getChildren().removeAll(hangar);
        hangar.clear();
    }
    public void recalibreAllGunners(){
        for (Gunner gunner : hangar) {
            gunner.freeFire();
        }
        typeproduce(Gunner.GunnerFireType.G_HOMING, 20);
        //gunnerFactory.continueProduction();
        // gunnerFactory.bulkproduce(0, 0,0, 50, false);
        // gunnerFactory.bulkproduce(0, 0,50, 0, false);
        // gunnerFactory.bulkproduce(0, 50,0, 0, false);
        // gunnerFactory.bulkproduce(50, 0,0, 0, false);
        // gunnerFactory.bulkproduce(5, 5,5, 20, false);
        // gunnerFactory.waveproduce(0, 5,2,20);
        //gunnerFactory.typeproduce(Gunner.GunnerFireType.HOMINGBOSS,1);
    }

    private boolean isPaused;
    public void pauseAction(){
        if(!isPaused) {
            isPaused=true;
            for (Gunner gunner : hangar) {
                gunner.freezeFire();
            }
            stackPane.getChildren().add(filter);
            stackPane.getChildren().add(label);
        }
        else {
            isPaused=false;
            for (Gunner gunner : hangar) {
                gunner.freeFire();
            }
            stackPane.getChildren().remove(filter);
            stackPane.getChildren().remove(label);

            Label resume=new Label("▶");
            resume.setFont(new Font(resume.getFont().getName(), 144));
            resume.setPrefWidth(maintab.getWidth());
            resume.setPrefHeight(maintab.getHeight());
            resume.setAlignment(Pos.CENTER);

            stackPane.getChildren().add(resume);

            Timeline timer = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1),event -> {
                if(resume.getOpacity()==0.0) {
                    stackPane.getChildren().remove(resume);
                    timer.stop();
                }
                else{
                    resume.setOpacity(resume.getOpacity()-0.1);
                }
            });
            timer.getKeyFrames().add(keyFrame);
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();


        }
    }

}
