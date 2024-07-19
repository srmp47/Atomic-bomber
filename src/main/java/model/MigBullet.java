package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.ZSU57;
import view.animations.BulletShooting;

import java.util.Objects;

public class MigBullet extends Rectangle {
    protected final double WIDTH=20;
    protected final double HEIGHT=10;
    protected final Game GAME;
    protected final Mig mig;
    protected BulletShooting bulletShooting;
    public MigBullet(Game game, Mig mig) {
        super(20,10);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(mig.getX()+mig.getWIDTH()/2);
        Objects.requireNonNull(game);
        this.setY(mig.getY()+mig.getHEIGHT()/2);
        game.getBullets().getChildren().add(this);
        this.mig=mig;
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/bullet2.png").toExternalForm())));
    }

    public BulletShooting getBulletShooting() {
        return bulletShooting;
    }

    public void setBulletShooting(BulletShooting bulletShooting) {
        this.bulletShooting = bulletShooting;
    }
}
