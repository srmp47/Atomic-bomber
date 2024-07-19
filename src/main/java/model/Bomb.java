package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Bomb extends Rectangle {
    protected final double WIDTH=20;
    protected final double HEIGHT=10;
    protected final Game GAME;
    public Bomb(Game game,Plane plane) {
        super(20,10);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(plane.getX()+plane.getWIDTH()/2);
        Objects.requireNonNull(game);
        this.setY(plane.getY()+plane.getHEIGHT()/2);
        game.getBombs().getChildren().add(this);
    }

}
