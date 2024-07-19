package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.ZSU57;

public class ExplosionZSU57 extends Transition {
    private final ZSU57 zsu57;
    private final Group zsu57s;
    private final Pane pane;

    public ExplosionZSU57(ZSU57 zsu57, Group zsu57s, Pane pane) {
        this.zsu57 = zsu57;
        this.zsu57s = zsu57s;
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000.0));
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0.0 <= v && v <= 0.33) {
            number = 1;
        } else if (0.33 < v && v <= 0.66) {
            number = 2;
        } else if (0.66 < v && v <= 1.0) {
            number = 3;
        }

        this.zsu57.setFill(new ImagePattern(new Image(TruckShooting.class.getResource("/image/fire" + number + ".png").toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ExplosionZSU57.this.zsu57s.getChildren().remove(ExplosionZSU57.this.zsu57);
            }
        });


    }
}

