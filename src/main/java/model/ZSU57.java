package model;

import controller.ApplicationController;
import enums.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Plane;
import model.User;
import view.animations.ZSU57Shooting;

import java.io.File;
import java.util.Objects;

public class ZSU57 extends Rectangle {
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final Game GAME;
    private final int SCORE=8;
    private boolean isHit;
    private Circle boundaryCircle;
    private ZSU57Shooting zsu57Shooting;
    public ZSU57(Game game){
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(0);
        Objects.requireNonNull(game);
        this.setY(game.getHEIGHT()-game.getHEIGHT()/4-HEIGHT);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/zsu57.png").toExternalForm())));
        boundaryCircle = new Circle(this.WIDTH / 2 + getRadius());
        boundaryCircle.setStroke(Color.ORANGE);
        boundaryCircle.getStrokeDashArray().addAll(10d, 5d);
        boundaryCircle.setStrokeLineCap(StrokeLineCap.ROUND);
        boundaryCircle.setFill(Color.TRANSPARENT);
        boundaryCircle.setCenterX(this.getX() + this.WIDTH / 2);
        boundaryCircle.setCenterY(this.getY() + this.HEIGHT / 2);
        GAME.getPane().getChildren().add(boundaryCircle);
        isHit=false;
    }

    public ZSU57Shooting getZsu57Shooting() {
        return zsu57Shooting;
    }

    public void setZsu57Shooting(ZSU57Shooting zsu57Shooting) {
        this.zsu57Shooting = zsu57Shooting;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public int getSCORE() {
        return SCORE;
    }



    public Circle getBoundaryCircle() {
        return boundaryCircle;
    }

    public double getRadius() {
        User user= User.getLoggedInUser();
        if(user.getLevel().equals(Level.EASY)) return 50;
        else if(user.getLevel().equals(Level.MEDIUM)) return 100;
        else if(user.getLevel().equals(Level.HARD)) return 150;
        else return 0;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
