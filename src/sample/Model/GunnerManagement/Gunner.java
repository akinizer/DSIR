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
        STRAIGHT, STRAIGHTBOSS, HOMING, HOMINGBOSS
    }

    enum ProjectileType {
        ZERO, ONE
    }

    private ProjectileType PT = ProjectileType.ZERO;

    private double width;
    private double height;
    private StackPane stackPane;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    Gunner(StackPane stackPane, TabPane maintab, double width, double height) {
        this.stackPane = stackPane;
        this.width = maintab.getWidth();
        this.height = maintab.getHeight();

        setGraphic(null);
        setVisible(true);

        Random random = new Random();

        setTranslateX(random.nextInt((int) width));
        setTranslateY(new Random().nextInt((int) height));

        setMotionListener();
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

    void fire(Runner runner, int speed, GunnerFireType gunnerFireType) {
        Projectile projectile = new Projectile();
        stackPane.getChildren().add(projectile);

        if (gunnerFireType == GunnerFireType.HOMING) {
            setText("[H]");
            setId("Hommer");
            projectile.setText("+");

            projectile.fireHoming(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.HOMINGBOSS) {
            setText("[H]");
            setId("HommerBoss");
            projectile.setText("+");

            setFont(new Font(getFont().getName(),72));
            projectile.setFont(new Font(getFont().getName(),72));

            projectile.fireHoming(runner, this, speed);
        }
        else if (gunnerFireType == GunnerFireType.STRAIGHT) {
            setText("[S]");
            setId("Strafe");
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
            setText("[S]");
            setId("StrafeBoss");
            if (PT == ProjectileType.ZERO) {
                PT = ProjectileType.ONE;
                projectile.setText("1");
            } else {
                PT = ProjectileType.ZERO;
                projectile.setText("0");
            }
            setFont(new Font(getFont().getName(),72));
            projectile.setFont(new Font(getFont().getName(),72));

            projectile.fire(runner, this, speed);
        }
    }
}
