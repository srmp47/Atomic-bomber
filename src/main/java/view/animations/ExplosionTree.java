package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Tree;

public class ExplosionTree extends Transition {
    private final Tree TREE;
    private final Group TREES;
    private final Pane PANE;

    public ExplosionTree(Tree tree, Pane pane, Group trees) {
        this.TREE = tree;
        this.PANE = pane;
        this.TREES = trees;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000.0));
    }


    protected void interpolate(double v) {
        this.TREE.setFill(new ImagePattern(new Image(TruckShooting.class.getResource("/image/blacksmoke.png").toExternalForm())));
        this.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ExplosionTree.this.TREES.getChildren().remove(ExplosionTree.this.TREE);
            }
        });


    }
}
