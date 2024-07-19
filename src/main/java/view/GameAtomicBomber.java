package view;

import controller.ApplicationController;
import controller.GameController;
import enums.Level;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.text.DecimalFormat;
import java.util.Random;


public class GameAtomicBomber extends Application {
    private static GameAtomicBomber currentGameAtomicBomber;
    private final double WIDTH = 900.0;
    private final double HEIGHT = 600.0;
    private final Game game;
    private final Pane pane;
    private Plane plane;
    private Rectangle land;
    private Timeline createTrucks;
    private Timeline createTanks;
    private Timeline createZSU57;
    private Timeline createMig;
    private Timeline getIronBomb;
    private Timeline createWarning;
    private Tree tree;
    private Bunker bunker;
    private int wave;

    public GameAtomicBomber(String username) {
        currentGameAtomicBomber = this;
        this.pane = new Pane();
        this.game = new Game(username, this, pane);
        this.createPlane();
        this.createLand();
        game.setLAND(land);
        pane.getChildren().add(land);
        this.game.setPlane(plane);
        this.wave = 1;
        createPauseButton(pane);
    }

    public static GameAtomicBomber getCurrentGameAtomicBomber() {
        return currentGameAtomicBomber;
    }

    private void changeColorOfGame(Stage stage) {
        try {
            ColorAdjust grayScale = new ColorAdjust();
            grayScale.setSaturation(-1);
            stage.getScene().getRoot().setEffect(grayScale);
        } catch (Exception e) {

        }
    }

    private void createPauseButton(Pane pane) {
        PauseButton pauseButton = new PauseButton(game);
        pane.getChildren().add(pauseButton);
        pauseButton.setOnMouseClicked((MouseEvent event) -> {
            try {
                new PauseMenu().start(ApplicationController.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private BackgroundImage createBackgroundImage() {
        Image image = new Image(Game.class.getResource("/image/sky.png").toExternalForm(), WIDTH, HEIGHT, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    private void setSize(Pane pane) {
        pane.setMinHeight(600.0);
        pane.setMaxHeight(600.0);
        pane.setMinWidth(900.0);
        pane.setMaxWidth(900.0);
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    private double getSecondsOfCreateMig() {
        User user = User.getLoggedInUser();
        if (user.getLevel().equals(Level.EASY)) return 36;
        else if (user.getLevel().equals(Level.MEDIUM)) return 27;
        else if (user.getLevel().equals(Level.HARD)) return 18;
        else return 0;
    }

    private void goToNextWave() {
        game.getTrees().getChildren().clear();
        game.getTanks().getChildren().clear();
        game.getTrucks().getChildren().clear();
        game.getBuildings().getChildren().clear();
        game.getBunkers().getChildren().clear();
        game.getZsu57s().getChildren().clear();
        wave++;
        if (wave == 2) doWave2();
        if (wave == 3) doWave3();
    }

    public void createPlane() {
        this.plane = GameController.shootPlane(pane, game);
        this.plane.setOnKeyPressed((keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                if (plane.getX() == WIDTH) plane.setX(10);
                else GameController.moveRight(this.plane);
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                if (plane.getX() == 0) plane.setX(WIDTH);
                else GameController.moveLeft(this.plane);
            } else if (keyEvent.getCode() == KeyCode.DOWN) GameController.moveDown(plane);
            else if (keyEvent.getCode() == KeyCode.UP) GameController.moveUp(plane);
            else if (keyEvent.getCode() == KeyCode.SPACE && User.getLoggedInUser().getNumberOfIronBomb() != 0) {
                GameController.shootIronBomb(pane, plane, game);
                game.setShoot(game.getShoot() + 1);
                game.updateLabelOfIronBomb();
            } else if (keyEvent.getCode() == KeyCode.R && User.getLoggedInUser().getNumberOfNukeBomb() != 0) {
                GameController.shootNukeBomb(pane, plane, game);
                game.setShoot(game.getShoot() + 1);
            } else if (keyEvent.getCode() == KeyCode.G) {
                User user = User.getLoggedInUser();
                user.setNumberOfNukeBomb(user.getNumberOfNukeBomb() + 1);
                game.updateLabelOfNukeBomb();
            } else if (keyEvent.getCode() == KeyCode.CONTROL) {
                User user = User.getLoggedInUser();
                user.setNumberOfClusterBomb(user.getNumberOfClusterBomb() + 1);
                game.updateLabelOfCluster();
            } else if (keyEvent.getCode() == KeyCode.T) {
                Random random = new Random();
                double firstX = random.nextDouble(0, game.getWIDTH());
                GameController.shootTank(pane, game, firstX);
            } else if (keyEvent.getCode() == KeyCode.H) {
                if (User.getLoggedInUser().getNumberOfPlanes() == 0)
                    User.getLoggedInUser().setNumberOfPlanes(User.getLoggedInUser().getNumberOfPlanes() + 1);
                game.updateLabelOfPlanes();
            } else if (keyEvent.getCode() == KeyCode.P) {
                if (wave == 1 || wave == 2) goToNextWave();
            }
        });
    }

    private void createLand() {
        Rectangle land = new Rectangle();
        land.setY(HEIGHT - HEIGHT / 4);
        land.setX(0);
        land.setWidth(WIDTH);
        land.setHeight(HEIGHT / 4);
        land.setFill(Color.GREEN);
        this.land = land;
    }

    private void getWarning(Pane pane, Game game) {
        Image image = new Image(Game.class.getResource("/image/migwarning.png").toExternalForm(), 500, 500, false, false);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.setX(WIDTH / 2 - imageView.getFitWidth() / 2);
        imageView.setY(HEIGHT / 2);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            GameAtomicBomber.this.pane.getChildren().remove(imageView);
        });
        fadeOut.play();
        pane.getChildren().add(imageView);

    }

    public double getAccuracy() {
        double accuracy;
        if (game.getShoot() == 0) accuracy = 0;
        else accuracy = ((double) game.getSuccessfulShoot() / game.getShoot()) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(accuracy);
        return Double.parseDouble(formattedNumber);
    }

    public void getTextOfAccuracy(Pane pane, Game game) {
        double accuracy;
        if (game.getShoot() == 0) accuracy = 0;
        else accuracy = ((double) game.getSuccessfulShoot() / game.getShoot()) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(accuracy);
        Text text = new Text(game.getSuccessfulShoot() + "\n" + game.getShoot() + "\n" + "Accuracy : " + formattedNumber + "%");
        text.setY(game.getHEIGHT() / 2 - 50);
        text.setX(game.getWIDTH() / 2 - 100);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), text);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            GameAtomicBomber.this.pane.getChildren().remove(text);
        });
        fadeOut.play();
        pane.getChildren().add(text);
    }

    public void doWave1() {
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
            GameController.shootTruck(pane, game);
        }));
        createTrucks.setCycleCount(2);
        createTrucks.play();
        createTanks = new Timeline(new KeyFrame(Duration.seconds(12), actionEvent -> {
            GameController.shootTank(pane, game, 0);
        }));
        createTanks.setCycleCount(1);
        createTanks.play();
        GameController.createBuilding(pane, game);
        GameController.createTree(pane, game);
        GameController.createTree(pane, game);
        GameController.createBunker(pane, game);
    }

    public void doWave2() {
        game.updateLabelOfWave();
        getTextOfAccuracy(pane, game);
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
            GameController.shootTruck(pane, game);
        }));
        createTrucks.setCycleCount(3);
        createTrucks.play();
        createTanks = new Timeline(new KeyFrame(Duration.seconds(12), actionEvent -> {
            GameController.shootTank(pane, game, 0);
        }));
        createTanks.setCycleCount(2);
        createTanks.play();
        createZSU57 = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> {
            GameController.shootZSU57(pane, game);
        }));
        createZSU57.setCycleCount(2);
        createZSU57.play();
        GameController.createBuilding(pane, game);
        GameController.createTree(pane, game);
        GameController.createTree(pane, game);
        GameController.createBunker(pane, game);
    }

    public void doWave3() {
        game.updateLabelOfWave();
        getTextOfAccuracy(pane, game);
        createTrucks = new Timeline(new KeyFrame(Duration.seconds(7), actionEvent -> {
            GameController.shootTruck(pane, game);
        }));
        createTrucks.setCycleCount(4);
        createTrucks.play();
        createTanks = new Timeline(new KeyFrame(Duration.seconds(12), actionEvent -> {
            GameController.shootTank(pane, game, 0);
        }));
        createTanks.setCycleCount(4);
        createTanks.play();
        createZSU57 = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> {
            GameController.shootZSU57(pane, game);
        }));
        createZSU57.setCycleCount(3);
        createZSU57.play();
        createMig = new Timeline(new KeyFrame(Duration.seconds(getSecondsOfCreateMig()), actionEvent -> {
            GameController.shootMig(pane, game);
        }));
        createMig.setCycleCount(-1);
        createMig.play();
        createWarning = new Timeline(new KeyFrame(Duration.seconds(getSecondsOfCreateMig() - 2), actionEvent -> {
            getWarning(pane, game);
        }));
        createWarning.setCycleCount(-1);
        createWarning.play();
        GameController.createBuilding(pane, game);
        GameController.createTree(pane, game);
        GameController.createTree(pane, game);
        GameController.createTree(pane, game);
        GameController.createBunker(pane, game);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.pane);
        this.setSize(this.pane);
        pane.getChildren().add(game.getTrucks());
        pane.getChildren().add(game.getTanks());
        pane.getChildren().add(game.getTrees());
        pane.getChildren().add(game.getBuildings());
        pane.getChildren().add(game.getBunkers());
        pane.getChildren().add(game.getNukeBombSymbol());
        pane.getChildren().add(game.getZsu57s());
        pane.getChildren().add(game.getBullets());
        pane.getChildren().add(game.getClusterBombSymbol());
        pane.getChildren().add(game.getMigs());
        pane.setBackground(new Background(createBackgroundImage()));
        GameController.createIcones(pane, game);
        pane.getChildren().add(game.getLabelOfIronBomb());
        pane.getChildren().add(game.getLabelOfNukeBomb());
        pane.getChildren().add(game.getLabelOfPlanes());
        pane.getChildren().add(game.getLabelOfKills());
        pane.getChildren().add(game.getScoreIcon());
        pane.getChildren().add(game.getBombIcon());
        pane.getChildren().add(game.getNukeIcon());
        pane.getChildren().add(game.getWarnings());
        pane.getChildren().add(game.getAccuracies());
        pane.getChildren().add(game.getLabelOfWave());
        pane.getChildren().add(game.getLabelOfCluster());
        getIronBomb = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent ->
        {
            if (User.getLoggedInUser().getNumberOfIronBomb() == 0)
                User.getLoggedInUser().setNumberOfIronBomb(User.getLoggedInUser().getNumberOfIronBomb() + 1);
            game.updateLabelOfIronBomb();
        }));
        getIronBomb.setCycleCount(-1);
        getIronBomb.play();
        stage.setScene(scene);
        stage.centerOnScreen();
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        stage.show();
        plane.requestFocus();
        doWave1();
    }
}
