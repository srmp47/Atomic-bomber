package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.ClusterBombSymbolShooting;
import view.animations.NukeBombSymbolShooting;

import java.util.Objects;

public class ClusterBombSymbol extends Rectangle {
    private final double WIDTH=20;
    private final double HEIGHT=10;
    private final Game GAME;
    private ClusterBombSymbolShooting clusterBombSymbolShooting;
    public ClusterBombSymbol(Game game,Bunker bunker) {
        super(35,35);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(bunker.getX());
        Objects.requireNonNull(game);
        this.setY(bunker.getY());
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/bonuscluster.png").toExternalForm())));
    }

    public ClusterBombSymbolShooting getClusterBombSymbolShooting() {
        return clusterBombSymbolShooting;
    }

    public void setClusterBombSymbolShooting(ClusterBombSymbolShooting clusterBombSymbolShooting) {
        this.clusterBombSymbolShooting = clusterBombSymbolShooting;
    }
}
