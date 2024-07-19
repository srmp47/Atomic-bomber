package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.ZSU57;
import view.animations.BulletShooting;

import java.util.Objects;

public class ZSU57Bullet extends Rectangle {
    protected final double WIDTH=20;
    protected final double HEIGHT=10;
    protected final Game GAME;
    protected final ZSU57 zsu57;
    protected BulletShooting bulletShooting;
    public ZSU57Bullet(Game game, ZSU57 zsu57) {
        super(20,10);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(zsu57.getX()+zsu57.getWIDTH()/2);
        Objects.requireNonNull(game);
        this.setY(zsu57.getY()+zsu57.getHEIGHT()/2);
        game.getBullets().getChildren().add(this);
        this.zsu57=zsu57;
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/bullet1.png").toExternalForm())));
    }

    public BulletShooting getBulletShooting() {
        return bulletShooting;
    }

    public void setBulletShooting(BulletShooting bulletShooting) {
        this.bulletShooting = bulletShooting;
    }
}
