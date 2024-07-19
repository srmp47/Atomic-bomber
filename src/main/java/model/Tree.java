package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class Tree extends Rectangle {
    private final double WIDTH=30;
    private final double HEIGHT=35;
    private final  Game GAME;
    private boolean isHit;
    public Tree(Game game){
        super(30,35);
        this.GAME = game;
        Objects.requireNonNull(game);
        Random random=new Random();
        this.setX(random.nextDouble(1,game.getWIDTH()));
        Objects.requireNonNull(game);
        this.setY(game.getHEIGHT()-game.getHEIGHT()/4-HEIGHT);
        Random random1=new Random();
        this.setFill(new ImagePattern(new Image(Tree.class.getResource("/image/tree"+random1.nextInt(1,3)+".png").toExternalForm())));
         isHit=false;
    }


    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
