package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.NukeBombShooting;

public class NukeBomb extends Bomb {
    private final String NAME="nukebomb";
    private NukeBombShooting nukeBombShooting;

    public NukeBomb(Game game, Plane plane) {
        super(game, plane);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/"+NAME+".png").toExternalForm())));
    }

    public NukeBombShooting getNukeBombShooting() {
        return nukeBombShooting;
    }

    public void setNukeBombShooting(NukeBombShooting nukeBombShooting) {
        this.nukeBombShooting = nukeBombShooting;
    }
}
