package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.TruckShooting;

import java.util.Objects;

public class Truck extends Rectangle {
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final  Game GAME;
    private final int SCORE=3;
    private boolean isHit;
    private TruckShooting truckShooting;
    public Truck(Game game){
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(0);
        Objects.requireNonNull(game);
        this.setY(game.getHEIGHT()-game.getHEIGHT()/4-HEIGHT);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/truck.png").toExternalForm())));
        isHit=false;
    }

    public TruckShooting getTruckShooting() {
        return truckShooting;
    }

    public void setTruckShooting(TruckShooting truckShooting) {
        this.truckShooting = truckShooting;
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

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
