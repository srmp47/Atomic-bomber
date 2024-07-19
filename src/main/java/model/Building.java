package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.TankShooting;

import java.util.Objects;
import java.util.Random;

public class Building extends Rectangle{
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final  Game GAME;
    private final int SCORE=4;
    private boolean isHit;
    public Building(Game game){
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        Random random=new Random();
        this.setX(random.nextDouble(1,game.getWIDTH()));
        Objects.requireNonNull(game);
        this.setY(game.getHEIGHT()-game.getHEIGHT()/4-HEIGHT);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/building.png").toExternalForm())));
        isHit=false;
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
