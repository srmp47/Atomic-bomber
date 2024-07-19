package view.animations;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import model.*;

public class IronBombShooting extends BombShooting {
    private final double CHANGING_ROTATE = 0.7;

    public IronBombShooting(Game game, Pane pane, Bomb bomb, Plane plane) {
        super(game, pane, bomb, plane);
    }

    private void playSoundOfExplosion() {
        Media media = new Media(Plane.class.getResource("/media/explosion.wav").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    @Override
    protected void interpolate(double v) {
        bomb.setX(bomb.getX() + X_SPEED);
        bomb.setY(bomb.getY() + ySpeed);
        ySpeed += ACCELERATION;
        bomb.setRotate(bomb.getRotate() + CHANGING_ROTATE);
        for (Node child : game.getTrucks().getChildren()) {
            Truck truck = (Truck) child;
            if (bomb.getBoundsInParent().intersects(truck.getBoundsInParent()) && !truck.isHit()) {
                truck.setHit(true);
                killTrucks(truck);
                playSoundOfExplosion();
                game.updateLabelOfKills();
                break;
            }
        }
        for (Node child : game.getTanks().getChildren()) {
            Tank tank = (Tank) child;
            if (bomb.getBoundsInParent().intersects(tank.getBoundsInParent()) && !tank.isHit()) {
                tank.setHit(true);
                killTanks(tank);
                Media media = new Media(Plane.class.getResource("/media/explosion.wav").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                playSoundOfExplosion();
                game.updateLabelOfKills();
                break;
            }
        }
        Rectangle land = game.getLand();
        if (bomb.getBoundsInParent().intersects(land.getBoundsInParent())) {
            killBomb(bomb);
            playSoundOfExplosion();
        }
        for (Node child : game.getBuildings().getChildren()) {
            Building building = (Building) child;
            if (bomb.getBoundsInParent().intersects(building.getBoundsInParent()) && !building.isHit()) {
                building.setHit(true);
                killBuilding(building);
                playSoundOfExplosion();
                game.updateLabelOfKills();
                break;
            }
        }
        for (Node child : game.getBunkers().getChildren()) {
            Bunker bunker = (Bunker) child;
            if (bomb.getBoundsInParent().intersects(bunker.getBoundsInParent()) && !bunker.isHit()) {
                bunker.setHit(true);
                killBunkers(bunker);
                playSoundOfExplosion();
                game.updateLabelOfKills();
                break;
            }
        }
        for (Node child : game.getTrees().getChildren()) {
            Tree tree = (Tree) child;
            if (bomb.getBoundsInParent().intersects(tree.getBoundsInParent()) && !tree.isHit()) {
                tree.setHit(true);
                killTree(tree);
                playSoundOfExplosion();
                break;
            }
        }
        for (Node child : game.getZsu57s().getChildren()) {
            ZSU57 zsu57 = (ZSU57) child;
            if (bomb.getBoundsInParent().intersects(zsu57.getLayoutBounds()) && !zsu57.isHit()) {
                zsu57.setHit(true);
                killZSU57(zsu57);
                game.updateLabelOfKills();
                playSoundOfExplosion();
            }
        }
    }
}

