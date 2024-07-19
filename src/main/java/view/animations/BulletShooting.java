package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Game;
import model.Plane;

public abstract class BulletShooting extends Transition {

    protected final double SPEED = 3;
    protected final int DURATION = 300;
    protected final double ACCELERATION = 0.04;
    protected Game game;
    protected Pane pane;
    protected Rectangle shooter;
    protected Plane plane;
    protected Rectangle bullet;

    public BulletShooting(Game game, Pane pane, Rectangle bullet, Plane plane, Rectangle shooter) {
        this.pane = pane;
        this.game = game;
        this.shooter = shooter;
        this.plane = plane;
        this.bullet = bullet;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    protected double getRotateBetweenPlaneAndBullet(double x1, double y1, Rectangle bullet) {
        double xDifference = x1 - shooter.getX();
        double yDifference = y1 - shooter.getY();
        double rotate = Math.atan2(yDifference, xDifference);
        bullet.setRotate(Math.toDegrees(rotate));
        return rotate;
    }

    @Override
    protected abstract void interpolate(double v);
}
