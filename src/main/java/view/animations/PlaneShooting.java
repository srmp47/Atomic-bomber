package view.animations;

import controller.ApplicationController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Plane;
import model.User;
import view.GameAtomicBomber;
import view.WinAndLossMenu;

public class PlaneShooting extends Transition {
    private final double SPEED = 1.5;
    private final int DURATION = 400;
    private final Game game;
    private final Pane pane;
    private final Plane plane;

    public PlaneShooting(Game game, Pane pane, Plane plane) {
        this.pane = pane;
        this.game = game;
        this.plane = plane;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double angleInRadian = Math.toRadians(plane.getRotate());
        plane.setX(plane.getX() + SPEED * Math.cos(angleInRadian));
        plane.setY(Math.max(0, plane.getY() + SPEED * Math.sin(angleInRadian)));
        if (plane.getX() >= game.getWIDTH()) plane.setX(-plane.getHEIGHT());
        if (plane.getX() <= -plane.getWIDTH()) plane.setX(game.getWIDTH());
        if (plane.getBoundsInParent().intersects(game.getLand().getBoundsInParent())) {
            if (User.getLoggedInUser().getNumberOfPlanes() > 0) {
                User.getLoggedInUser().setNumberOfPlanes(User.getLoggedInUser().getNumberOfPlanes() - 1);
                game.updateLabelOfPlanes();
                plane.setY(70);
                plane.setX(0);
                plane.setRotate(0);
            } else if (!plane.isHit()) {
                try {
                    new WinAndLossMenu(GameAtomicBomber.getCurrentGameAtomicBomber().getAccuracy()).start(ApplicationController.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.stop();
                plane.isHit();
                pane.getChildren().remove(plane);
            }

        }
    }
}
