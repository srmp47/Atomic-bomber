package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Truck;

public class TruckShooting extends Transition {
    private final double SPEED = 1;
    private final int DURATION = 700;
    private final Game game;
    private final Pane pane;
    private final Truck truck;
    private boolean isGoingToRight = true;

    public TruckShooting(Game game, Pane pane, Truck truck) {
        this.pane = pane;
        this.game = game;
        this.truck = truck;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }


    @Override
    protected void interpolate(double v) {
        if (isGoingToRight) truck.setX(truck.getX() + SPEED);
        else truck.setX(truck.getX() - SPEED);
        if (truck.getX() + truck.getWIDTH() >= game.getWIDTH()) {
            truck.setScaleX(-1);
            this.isGoingToRight = false;
        } else if (truck.getX() <= 0) {
            truck.setScaleX(+1);
            this.isGoingToRight = true;
        }

    }
}
