package sample.Model.GunnerManagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.RunnerManagement.Runner;

class Projectile extends Label {

    void fireHoming(Runner runner, Gunner gunner, int speed) {
        setTranslateX(gunner.getTranslateX());
        setTranslateY(gunner.getTranslateY());

        Timeline timeline = new Timeline();
        double sourcex = gunner.getTranslateX();
        double sourcey = gunner.getTranslateY();

        runner.setStyle("-fx-border-color: red;");

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 / speed), event -> {
            StackPane parent = ((StackPane) getParent());
            boolean validIntervalX = (getTranslateX() > 0 && getTranslateX() < parent.getWidth());
            boolean validIntervalY = (getTranslateY() > 0 && getTranslateY() < parent.getHeight());

            if (gunner.isCease() || !(validIntervalX && validIntervalY)) {
                timeline.stop();
                parent.getChildren().remove(this);
            }
            double xhead = runner.getTranslateX() - 5;
            double yhead = runner.getTranslateY() - 10;
            double xtail = (runner.getTranslateX() + runner.getWidth() - 5);
            double ytail = (runner.getTranslateY() + runner.getHeight() - 10);

            if ((xhead <= getTranslateX() && getTranslateX() <= xtail
                    && yhead <= getTranslateY() && getTranslateY() <= ytail)) {
                timeline.stop();
                parent.getChildren().remove(this);
                return;
            }

            double targetx = runner.getTranslateX();
            double targety = runner.getTranslateY();

            setTranslateX(getTranslateX() + getSlopeSign(sourcex, targetx));
            setTranslateY(getTranslateY() + getSlope(sourcex, sourcey, targetx, targety) * getSlopeSign(sourcex, targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void fireGuided(Runner runner, Gunner gunner, int speed) {
        setTranslateX(gunner.getTranslateX());
        setTranslateY(gunner.getTranslateY());

        Timeline timeline = new Timeline();

        runner.setStyle("-fx-border-color: red;");

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 / speed), event -> {
            StackPane parent = ((StackPane) getParent());
            boolean validIntervalX = (getTranslateX() > 0 && getTranslateX() < parent.getWidth());
            boolean validIntervalY = (getTranslateY() > 0 && getTranslateY() < parent.getHeight());

            if (gunner.isCease() || !(validIntervalX && validIntervalY)) {
                timeline.stop();
                parent.getChildren().remove(this);
            }
            double xhead = runner.getTranslateX() - 5;
            double yhead = runner.getTranslateY() - 10;
            double xtail = (runner.getTranslateX() + runner.getWidth() - 5);
            double ytail = (runner.getTranslateY() + runner.getHeight() - 10);

            if ((xhead <= getTranslateX() && getTranslateX() <= xtail
                    && yhead <= getTranslateY() && getTranslateY() <= ytail)) {
                timeline.stop();
                parent.getChildren().remove(this);
                return;
            }

            double targetx = runner.getTranslateX();
            double targety = runner.getTranslateY();

            setTranslateX(getTranslateX() + getSlopeSign(getTranslateX(), targetx));
            setTranslateY(getTranslateY() + getSlope(getTranslateX(), getTranslateY(), targetx, targety) * getSlopeSign(getTranslateX(), targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void fire(Runner runner, Gunner gunner, int speed) {
        setTranslateX(gunner.getTranslateX());
        setTranslateY(gunner.getTranslateY());

        Timeline timeline = new Timeline();
        double sourcex = gunner.getTranslateX();
        double sourcey = gunner.getTranslateY();

        runner.setStyle("-fx-border-color: red;");

        double targetx = runner.getTranslateX();
        double targety = runner.getTranslateY();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100 / speed), event -> {
            StackPane parent = ((StackPane) getParent());
            boolean validIntervalX = (getTranslateX() > 0 && getTranslateX() < parent.getWidth());
            boolean validIntervalY = (getTranslateY() > 0 && getTranslateY() < parent.getHeight());

            if (gunner.isCease() || !(validIntervalX && validIntervalY)) {
                timeline.stop();
                parent.getChildren().remove(this);
            }
            double xhead = runner.getTranslateX() - 5;
            double yhead = runner.getTranslateY() - 10;
            double xtail = (runner.getTranslateX() + runner.getWidth());
            double ytail = (runner.getTranslateY() + runner.getHeight() - 7.5);

            if ((xhead <= getTranslateX() && getTranslateX() <= xtail
                    && yhead <= getTranslateY() && getTranslateY() <= ytail)) {
                timeline.stop();
                parent.getChildren().remove(this);
                return;
            }


            setTranslateX(getTranslateX() + getSlopeSign(sourcex, targetx));
            setTranslateY(getTranslateY() + getSlope(sourcex, sourcey, targetx, targety) * getSlopeSign(sourcex, targetx));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private double getSlope(double sourcex, double sourcey, double targetx, double targety) {
        return (targety - sourcey) / (targetx - sourcex);
    }

    private int getSlopeSign(double sourcex, double targetx) {
        if (sourcex > targetx) return -1;
        else return 1;
    }

}
