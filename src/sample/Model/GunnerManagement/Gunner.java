package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

import java.util.List;
import java.util.Random;


public class Gunner extends Label implements IGunner {
    public enum GunnerFireType {
        STRAIGHT, STRAIGHTBOSS, HOMING, HOMINGBOSS, GUIDED, GUIDEDBOSS, G_HOMING, G_HOMINGBOSS
    }

    enum ProjectileType {
        ZERO, ONE
    }

    private ProjectileType PT = ProjectileType.ZERO;

    private double width;
    private double height;
    private StackPane stackPane;

    private boolean stopCommand;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    Gunner(StackPane stackPane, TabPane maintab, double width, double height, GunnerFireType gunnerFireType) {
        this.stackPane = stackPane;
        this.width = maintab.getWidth();
        this.height = maintab.getHeight();

        stopCommand=false;

        setGraphic(null);
        setVisible(true);

        Random random = new Random();

        setTranslateX(random.nextInt((int) width));
        setTranslateY(new Random().nextInt((int) height));

        setMotionListener();
        gunnerSettings(gunnerFireType);
    }

    private int count = 0;
    private Direction currentDirection;

    @Override
    public void setMotionListener() {
        Timeline timer = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(25), event -> {
            if (getTranslateX() == 0) currentDirection = Direction.RIGHT;
            if (getTranslateY() == 0) currentDirection = Direction.DOWN;
            if (getTranslateX() == width) currentDirection = Direction.LEFT;
            if (getTranslateY() == height) currentDirection = Direction.UP;


            if (count++ % 200 == 0) {
                currentDirection = getRandomDirection();
            }
            getDireAction(currentDirection);
        });
        timer.getKeyFrames().add(keyFrame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void getDireAction(Direction dire) {
        switch ( dire ) {
            case UP:
                setTranslateY(getTranslateY() - 1);
                break;
            case DOWN:
                setTranslateY(getTranslateY() + 1);
                break;
            case LEFT:
                setTranslateX(getTranslateX() - 1);
                break;
            case RIGHT:
                setTranslateX(getTranslateX() + 1);
                break;
        }
    }

    private Direction getRandomDirection() {
        List<Direction> directionlist = List.of(Direction.values());
        Random random = new Random();
        return directionlist.get(random.nextInt(directionlist.size()));
    }

    private int overheat=0;
    void fire(Runner runner, int speed, GunnerFireType gunnerFireType) {
        Projectile projectile = new Projectile();
        stackPane.getChildren().add(projectile);

        if (gunnerFireType == GunnerFireType.HOMING) {
            projectile.setText("+");
            projectile.fireHoming(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.HOMINGBOSS) {
            projectile.setText("+");
            projectile.setFont(new Font(getFont().getName(),72));
            projectile.fireHoming(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.G_HOMING) {
            projectile.setText("+");
            projectile.fireHoming(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.G_HOMINGBOSS) {
            projectile.setText("+");
            projectile.setFont(new Font(getFont().getName(),72));

            projectile.fireHoming(runner, this, speed);
        }
        if (gunnerFireType == GunnerFireType.GUIDED) {
            projectile.setText("✱");
            projectile.fireGuided(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.GUIDEDBOSS) {
            projectile.setText("✱");
            projectile.setFont(new Font(getFont().getName(),72));
            projectile.fireGuided(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.STRAIGHT) {
            if (PT == ProjectileType.ZERO) {
                PT = ProjectileType.ONE;
                projectile.setText("1");
            } else {
                PT = ProjectileType.ZERO;
                projectile.setText("0");
            }
            projectile.fire(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.STRAIGHTBOSS) {
            if (PT == ProjectileType.ZERO) {
                PT = ProjectileType.ONE;
                projectile.setText("1");
            } else {
                PT = ProjectileType.ZERO;
                projectile.setText("0");
            }
            projectile.setFont(new Font(getFont().getName(),72));
            projectile.fire(runner, this, speed);
        }
    }

    int getOverheatCount(){
        return ++overheat;
    }

    double getRange(GunnerFireType gtype){
        if (gtype == GunnerFireType.G_HOMING || gtype == GunnerFireType.G_HOMINGBOSS) {
            return -1;
        } else {
            return 200;
        }
    }

    private void gunnerSettings(GunnerFireType gunnerFireType){
        if (gunnerFireType == GunnerFireType.HOMING) {
            setText("[H]");
            setId("Hommer");
        }
        else if (gunnerFireType == GunnerFireType.HOMINGBOSS) {
            setText("[H]");
            setId("HommerBoss");
            setFont(new Font(getFont().getName(),72));
        }
        else if (gunnerFireType == GunnerFireType.G_HOMING) {
            setText("[-]");
            setId("Hommer");
        }
        else if (gunnerFireType == GunnerFireType.G_HOMINGBOSS) {
            setText("[-]");
            setId("HommerBoss");
            setFont(new Font(getFont().getName(),72));
        }
        if (gunnerFireType == GunnerFireType.GUIDED) {
            setText("[G]");
            setId("Guided");
        }
        else if (gunnerFireType == GunnerFireType.GUIDEDBOSS) {
            setText("[G]");
            setId("GuidedBoss");
            setFont(new Font(getFont().getName(),72));
        }
        else if (gunnerFireType == GunnerFireType.STRAIGHT) {
            setText("[S]");
            setId("Strafe");
        }
        else if (gunnerFireType == GunnerFireType.STRAIGHTBOSS) {
            setText("[S]");
            setId("StrafeBoss");
            setFont(new Font(getFont().getName(),72));
        }


    }

    void ceaseFire(){
        stopCommand=true;
    }
    boolean isCease(){
        return stopCommand;
    }

}
