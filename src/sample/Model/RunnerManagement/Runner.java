package sample.Model.RunnerManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.Model.GunnerManagement.Gunner;
import sample.Model.GunnerManagement.GunnerFactory;

public class Runner extends Label {

    //Components
    private TabPane maintab;
    private Tab towntab;
    private StackPane stackPane;
    private double width;
    private double height;
    private Button actionButton, speedButton, vehicleButton;
    private ImageView iv;

    //Attributes
    private int speed;
    private int distancecounter;
    private double firerate, cooldown;

    private Timeline cooldownTimer;
    private Projectile projectile;

    private GunnerFactory gunnerFactory;

    //Constructor
    public Runner(TabPane maintab, double width, double height, Button actionButton, Button speedButton, Button vehicleButton, Tab towntab, StackPane stackPane) {
        //component settings
        this.maintab = maintab;
        this.width = width / 2 - 5;
        this.height = height / 2 - 5;
        this.actionButton = actionButton;
        this.speedButton = speedButton;
        this.vehicleButton = vehicleButton;
        this.towntab = towntab;
        this.stackPane = stackPane;

        //attribute initial values
        speed = 1;
        distancecounter = 0;
        firerate = 1;
        cooldown = 0;
        direction = 1;

        isStatusPausedForCar=false;

        //settings initialization
        setPresets();

        if (actionButton == null || speedButton == null || vehicleButton == null) return;

        setMouseListener();
        setKeyListener();
    }

    //settings
    private void setPresets() {
        setCarView();
        setGraphic(iv);
        setTranslateX(width);
        setTranslateY(height);
        setContentDisplay(ContentDisplay.CENTER);
        setTooltip();
        setVisible(false);
    }

    //mouse events: start button, reset button, tooltip
    private void setMouseListener() {
        actionButton.setOnMouseReleased(mouseEvent -> {
            direction = 1;
            cooldown = 0;
            iv.setRotate(0);
            setTranslateX(width);
            setTranslateY(height);
            setVisible(true);

            maintab.requestFocus();

            actionButton.setText("restart!");
            speedButton.setDisable(false);
            vehicleButton.setDisable(false);
            distancecounter = 0;

            //On Restart Remove all Gunners from Fields and Start generating them again
            gunnerFactory.demolishAllGunners();
            gunnerFactory.recalibreAllGunners();


            setTooltip();

            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();
        });
    }

    //key events: movement, firing projectiles
    private int direction;

    private void setKeyListener() {
        maintab.setOnKeyPressed(keyEvent -> {
            if (towntab.isSelected()) {
                //TODO: this setting disables cross movement,
                // to ENABLE cross movement set all cases if and assign cross movement images with additional if cases

                if (" ".equals(keyEvent.getText().toLowerCase())) {
                    setPaused();
                    gunnerFactory.pauseAction();
                }

                if(isPaused()) return;

                //movement and movement speed, direction, distance
                switch ( keyEvent.getText().toLowerCase() ) {
                    case "d":
                        iv.setRotate(90);
                        setTranslateX(getTranslateX() + 2 * speed);
                        distancecounter += 2 * speed;
                        direction = 2;
                        break;
                    case "a":
                        iv.setRotate(-90);
                        setTranslateX(getTranslateX() - 2 * speed);
                        distancecounter += 2 * speed;
                        direction = 4;
                        break;
                    case "s":
                        iv.setRotate(180);
                        setTranslateY(getTranslateY() + 2 * speed);
                        distancecounter += 2 * speed;
                        direction = 3;
                        break;
                    case "w":
                        iv.setRotate(0);
                        setTranslateY(getTranslateY() - 2 * speed);
                        distancecounter += 2 * speed;
                        direction = 1;
                        break;
                    case "f":
                        break;

                }


                //cooldown validation
                if (isCooldownActive()) return;

                //COOLDOWN IS NO MORE ACTIVE AFTER THIS POINT
                if (cooldownTimer != null) {
                    cooldownTimer.stop();
                    cooldownTimer = null;
                }

                //projectile generation and firing
                if (keyEvent.getText().toLowerCase().equals("f")) {
                    //projectile
                    projectile = new Projectile(getTranslateX() + getWidth() / 2.5, getTranslateY() + getHeight() / 2.5);
                    stackPane.getChildren().add(projectile);

                    switch ( vehicleButton.getText() ) {
                        case "Car":
                            projectile.fire(direction, "minigun");
                            break;
                        case "Jeep":
                            projectile.fire(direction, "sniper");
                            break;
                        case "Truck":
                            projectile.fire(direction, "missile");
                            break;
                    }

                    //get firerate and cooldown values from the projectile
                    firerate = projectile.getSpeed();
                    cooldown = projectile.getCooldown();
                    //cooldown on fire
                    initiateCooldown();
                }
            }
            //tooltip on Runner to report stat values
            setTooltip();
        });
    }

    //Tooltip Message
    private void setTooltip() {
        setTooltip(new Tooltip("Position: (" + getTranslateX() + ":" + getTranslateY() + ")"
                + "\nDistance: " + distancecounter + " px"
                + "\nSpeed: " + speed
                + "\nFirerate: " + firerate
        ));
    }

    //Runner View Interface
    public void setCarView() {
        iv = new ImageView(new Image(getClass().getResource("/sample/Resources/imagefile/car-up.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);
        iv.setRotate((direction - 1) * 90);
        setGraphic(iv);

        setPrefWidth(iv.getFitWidth());
        setPrefHeight(iv.getFitHeight());
    }

    public void setJeepView() {
        iv = new ImageView(new Image(getClass().getResource("/sample/Resources/imagefile/jeep.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);
        iv.setRotate((direction - 1) * 90);
        setGraphic(iv);

        setPrefWidth(iv.getFitWidth());
        setPrefHeight(iv.getFitHeight());
    }

    public void setTruckView() {
        iv = new ImageView(new Image(getClass().getResource("/sample/Resources/imagefile/truck.png").toExternalForm()));
        iv.setFitWidth(25);
        iv.setPreserveRatio(true);
        iv.setRotate((direction - 1) * 90);
        setGraphic(iv);

        setPrefWidth(iv.getFitWidth());
        setPrefHeight(iv.getFitHeight());
    }

    //Runner Movement Speed Update
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //Cooldown Initializer and Validator
    private long starttime = -1;

    private void initiateCooldown() {
        starttime = System.currentTimeMillis();

        if (cooldownTimer == null) {
            cooldownTimer = new Timeline();
            cooldownTimer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
                long elapsed = ((System.currentTimeMillis() - starttime) / 1000);
                if (elapsed < projectile.getCooldown()) {
                    System.out.println("Time left: " + (projectile.getCooldown() - elapsed));
                } else {
                    cooldownTimer.stop();
                    System.out.println("Ready to Fire!");
                }
            }));
            cooldownTimer.setCycleCount(Animation.INDEFINITE);
            cooldownTimer.play();
        }

    }

    private boolean isCooldownActive() {
        long elapsed = (System.currentTimeMillis() - starttime) / 1000;
        return starttime != -1 && elapsed < (long) cooldown;
    }

    public void setGunnerFactory(GunnerFactory gunnerFactory) {
        this.gunnerFactory = gunnerFactory;
    }


    private boolean isStatusPausedForCar;
    private void setPaused(){
        isStatusPausedForCar= !isStatusPausedForCar;
    }
    private boolean isPaused(){
        return isStatusPausedForCar;
    }

}
