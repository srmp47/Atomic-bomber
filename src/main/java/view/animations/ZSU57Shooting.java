package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Plane;
import model.ZSU57;

public class ZSU57Shooting extends Transition {
    private final double SPEED = 0.5;
    private final int DURATION = 1100;
    private final Game game;
    private final Pane pane;
    private final ZSU57 zsu57;
    private int currentNumberOfFrequency = 0;
    private final int NUMBER_OF_FREQUENCY = 50;
    private boolean isGoingToRight = true;

    public ZSU57Shooting(Game game, Pane pane, ZSU57 zsu57) {
        this.pane = pane;
        this.game = game;
        this.zsu57 = zsu57;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    private double getDistanceOf(Plane plane, ZSU57 zsu57) {
        double X = Math.pow(plane.getX() - zsu57.getX(), 2);
        double Y = Math.pow(plane.getY() - zsu57.getY(), 2);
        double distance = Math.sqrt(X + Y);
        return distance;
    }

    @Override
    protected void interpolate(double v) {
        if (isGoingToRight) zsu57.setX(zsu57.getX() + SPEED);
        else zsu57.setX(zsu57.getX() - SPEED);
        if (zsu57.getX() + zsu57.getWIDTH() >= game.getWIDTH()) {
            zsu57.setScaleX(-1);
            this.isGoingToRight = false;
        } else if (zsu57.getX() <= 0) {
            zsu57.setScaleX(+1);
            this.isGoingToRight = true;
        }
        zsu57.getBoundaryCircle().setCenterX(zsu57.getX() + zsu57.getWIDTH() / 2);
        if (getDistanceOf(game.getPlane(), zsu57) < zsu57.getRadius() && !zsu57.isHit()) {
            currentNumberOfFrequency++;
            if (currentNumberOfFrequency >= NUMBER_OF_FREQUENCY) {
                currentNumberOfFrequency = 0;
                GameController.shootBulletOfZSU57(pane, game, zsu57);
            }
        }

    }
}
