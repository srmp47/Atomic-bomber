package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Tank;

public class ExplosionTank extends Transition {
    private final Tank TANK;
    private final Group TANKS;
    private final Pane PANE;

    public ExplosionTank(Tank tank, Pane pane, Group tanks) {
        this.TANK = tank;
        this.PANE = pane;
        this.TANKS = tanks;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000.0));
    }

    protected void interpolate(double v) {
        int number = 1;
        if (0.0 <= v && v <= 0.33) {
            number = 1;
        } else if (0.33 < v && v <= 0.66) {
            number = 2;
        } else if (0.66 < v && v <= 1.0) {
            number = 3;
        }

        this.TANK.setFill(new ImagePattern(new Image(TruckShooting.class.getResource("/image/deadtank" + number + ".png").toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ExplosionTank.this.TANKS.getChildren().remove(ExplosionTank.this.TANK);
            }
        });

    }
}
