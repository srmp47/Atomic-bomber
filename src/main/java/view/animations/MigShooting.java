package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Mig;
import model.Plane;

public class MigShooting extends Transition {
    private final double SPEED = 1.1;
    private final int DURATION = 900;
    private final Game game;
    private final Pane pane;
    private final Mig mig;
    private int currentNumberOfFrequency = 0;
    private final int NUMBER_OF_FREQUENCY = 50;

    public MigShooting(Game game, Pane pane, Mig mig) {
        this.pane = pane;
        this.game = game;
        this.mig = mig;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    private double getDistanceOf(Plane plane, Mig mig) {
        double X = Math.pow(plane.getX() - mig.getX(), 2);
        double Y = Math.pow(plane.getY() - mig.getY(), 2);
        double distance = Math.sqrt(X + Y);
        return distance;
    }

    @Override
    protected void interpolate(double v) {
        mig.setX(mig.getX() + SPEED);
        mig.getBoundaryCircle().setCenterX(mig.getX() + mig.getWIDTH() / 2);
        if (getDistanceOf(game.getPlane(), mig) < mig.getRadius()) {
            currentNumberOfFrequency++;
            if (currentNumberOfFrequency >= NUMBER_OF_FREQUENCY) {
                currentNumberOfFrequency = 0;
                GameController.shootBulletOfMig(pane, game, mig);
            }
        }

    }
}
