package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class NukeIcon extends Rectangle {
    private final double WIDTH=25;
    private final double HEIGHT=25;
    private final double X=67;
    private final double Y=14;
    public NukeIcon(Game game){
        super(25,25);
        Objects.requireNonNull(game);
        this.setX(X);
        Objects.requireNonNull(game);
        this.setY(Y);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/nukeicon.png").toExternalForm())));
        game.setNukeIcon(this);
    }
}
