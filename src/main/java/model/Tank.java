package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.TankShooting;
import view.animations.TruckShooting;

import java.util.Objects;

public class Tank extends Rectangle {
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final  Game GAME;
    private final int SCORE=5;
    private TankShooting tankShooting;
    private boolean isHit;
    public Tank(Game game,double firstX){
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(firstX);
        Objects.requireNonNull(game);
        this.setY(game.getHEIGHT()-game.getHEIGHT()/4-HEIGHT);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/tank1.png").toExternalForm())));
        isHit=false;
    }

    public TankShooting getTankShooting() {
        return  tankShooting;
    }

    public void setTankShooting(TankShooting tankShooting) {
        this.tankShooting = tankShooting;
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
