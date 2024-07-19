package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Building;

public class ExplosionBuilding extends Transition {

    private final Building BUILDING;
    private final Group BUILDINGS;
    private final Pane PANE;

    public ExplosionBuilding(Building building, Pane pane, Group buildings) {
        this.BUILDING = building;
        this.PANE = pane;
        this.BUILDINGS = buildings;
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

        this.BUILDING.setFill(new ImagePattern(new Image(TruckShooting.class.getResource("/image/fire" + number + ".png").toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ExplosionBuilding.this.BUILDINGS.getChildren().remove(ExplosionBuilding.this.BUILDING);
            }
        });


    }
}
