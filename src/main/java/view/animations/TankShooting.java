package view.animations;

import enums.Level;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Tank;
import model.User;

public class TankShooting extends Transition {
    private final int DURATION = 900;
    private final Game game;
    private final Pane pane;
    private final Tank tank;
    private boolean isGoingToRight = true;

    public TankShooting(Game game, Pane pane, Tank tank) {
        this.pane = pane;
        this.game = game;
        this.tank = tank;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    private double getSpeed() {
        User user = User.getLoggedInUser();
        if (user.getLevel().equals(Level.EASY)) return 0.4;
        else if (user.getLevel().equals(Level.MEDIUM)) return 0.8;
        else if (user.getLevel().equals(Level.HARD)) return 1.2;
        else return 0;
    }

    @Override
    protected void interpolate(double v) {
        if (isGoingToRight) tank.setX(tank.getX() + getSpeed());
        else tank.setX(tank.getX() - getSpeed());
        if (tank.getX() + tank.getWIDTH() >= game.getWIDTH()) {
            tank.setScaleX(-1);
            this.isGoingToRight = false;
        } else if (tank.getX() <= 0) {
            tank.setScaleX(+1);
            this.isGoingToRight = true;
        }

    }
}
