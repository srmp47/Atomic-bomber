package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.NukeBombSymbolShooting;

import java.util.Objects;

public class NukeBombSymbol extends Rectangle {
    private final double WIDTH=20;
    private final double HEIGHT=10;
    private final Game GAME;
    private  NukeBombSymbolShooting nukeBombSymbolShooting;
    public NukeBombSymbol(Game game,Building building) {
        super(35,35);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(building.getX());
        Objects.requireNonNull(game);
        this.setY(building.getY());
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/nukeicon.png").toExternalForm())));
    }

    public void setNukeBombSymbolShooting(NukeBombSymbolShooting nukeBombSymbolShooting) {
        this.nukeBombSymbolShooting = nukeBombSymbolShooting;
    }

    public NukeBombSymbolShooting getNukeBombSymbolShooting() {
        return nukeBombSymbolShooting;
    }
}
