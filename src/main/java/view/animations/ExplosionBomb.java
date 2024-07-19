package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Bomb;

public class ExplosionBomb extends Transition {
    private final Bomb BOMB;
    private final Pane PANE;
    private Group BOMBS = new Group();

    public ExplosionBomb(Bomb bomb, Pane pane, Group bombs) {
        this.BOMB = bomb;
        this.PANE = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000.0));
        this.BOMBS = bombs;
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
        this.BOMB.setFill(new ImagePattern(new Image(TruckShooting.class.getResource("/image/fire" + number + ".png").toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ExplosionBomb.this.BOMBS.getChildren().remove(ExplosionBomb.this.BOMB);
            }
        });
        if (v == 1) PANE.getChildren().remove(BOMB);

    }
}
