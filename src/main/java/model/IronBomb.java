package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.animations.IronBombShooting;

public class IronBomb extends  Bomb{
    private final String NAME="ironbomb";
    private IronBombShooting ironBombShooting;

    public IronBomb(Game game, Plane plane) {
        super(game, plane);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/"+NAME+".png").toExternalForm())));
    }

    public IronBombShooting getIronBombShooting() {
        return ironBombShooting;
    }

    public void setIronBombShooting(IronBombShooting ironBombShooting) {
        this.ironBombShooting = ironBombShooting;
    }
}
