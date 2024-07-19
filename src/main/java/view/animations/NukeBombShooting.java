package view.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public class NukeBombShooting extends BombShooting {
    private final double CHANGING_ROTATE = 0.2;
    private final double KILL_DISTANCE = 269;

    public NukeBombShooting(Game game, Pane pane, Bomb bomb, Plane plane) {
        super(game, pane, bomb, plane);
    }

    private void showImageOfExplosion() {
        Image image = new Image(Game.class.getResource("/image/flash.png").toExternalForm(), 500, 500, false, false);
        ImageView imageView = new ImageView(image);
        imageView.setX(bomb.getX() - 240);
        imageView.setY(bomb.getY() - 300);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            NukeBombShooting.this.pane.getChildren().remove(imageView);
        });
        fadeOut.play();
        pane.getChildren().add(imageView);
    }

    @Override
    protected void interpolate(double v) {
        bomb.setX(bomb.getX() + X_SPEED);
        bomb.setY(bomb.getY() + ySpeed);
        ySpeed += ACCELERATION;
        bomb.setRotate(bomb.getRotate() + CHANGING_ROTATE);
        if (bomb.getBoundsInParent().intersects(game.getLand().getBoundsInParent())) {
            showImageOfExplosion();
            killBomb(bomb);
            for (Node child : game.getTrucks().getChildren()) {
                Truck truck = (Truck) child;
                if (Math.abs(truck.getX() - bomb.getX()) <= KILL_DISTANCE) {
                    killTrucks(truck);
//                    getImageOfExplosion();
                }
            }
            for (Node child : game.getTanks().getChildren()) {
                Tank tank = (Tank) child;
                if (Math.abs(tank.getX() - bomb.getX()) <= KILL_DISTANCE) {
                    killTanks(tank);
//                    getImageOfExplosion();
                }
            }
            for (Node child : game.getBuildings().getChildren()) {
                Building building = (Building) child;
                if (Math.abs(building.getX() - bomb.getX()) <= KILL_DISTANCE) {
                    killBuilding(building);
//                    getImageOfExplosion();
                }
            }
            for (Node child : game.getBunkers().getChildren()) {
                Bunker bunker = (Bunker) child;
                if (Math.abs(bunker.getX() - bomb.getX()) <= KILL_DISTANCE) {
                    killBunkers(bunker);
                    break;
//                    getImageOfExplosion();
                }
            }
            for (Node child : game.getTrees().getChildren()) {
                Tree tree = (Tree) child;
                if (Math.abs(tree.getX() - bomb.getX()) <= KILL_DISTANCE) {
                    killTree(tree);
//                    getImageOfExplosion();
                }
            }
        }
    }
}
