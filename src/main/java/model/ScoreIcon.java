package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Random;

public class ScoreIcon extends Rectangle {
    private final double WIDTH=25;
    private final double HEIGHT=25;
    private final double X=120;
    private final double Y=14;
    public ScoreIcon(Game game){
        super(25,25);
        Objects.requireNonNull(game);
        this.setX(X);
        Objects.requireNonNull(game);
        this.setY(Y);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/scoreicon.png").toExternalForm())));
        game.setScoreIcon(this);
    }
}
