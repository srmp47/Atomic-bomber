package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class PauseButton extends Rectangle {
    private final double WIDTH=40;
    private final double HEIGHT=40;
    private final  Game GAME;
    public PauseButton(Game game){
        super(40,40);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(game.getWIDTH()-40);
        Objects.requireNonNull(game);
        this.setY(0);
        this.setFill(new ImagePattern(new Image(Tree.class.getResource("/image/pause.png").toExternalForm())));
    }
}
