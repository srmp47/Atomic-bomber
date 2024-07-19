package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.*;

public abstract class BombShooting extends Transition {
    protected final double X_SPEED;
    protected final int DURATION = 400;
    protected final double ACCELERATION = 0.04;
    protected Game game;
    protected Pane pane;
    protected Bomb bomb;
    protected double ySpeed = 0;

    public BombShooting(Game game, Pane pane, Bomb bomb, Plane plane) {
        this.pane = pane;
        this.game = game;
        this.bomb = bomb;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
        X_SPEED = plane.getSPEED() * Math.cos(Math.toRadians(plane.getRotate()));
        bomb.setRotate(plane.getRotate());
    }

    protected void killTrucks(Truck truck) {
        game.setSuccessfulShoot(game.getSuccessfulShoot() + 1);
        truck.getTruckShooting().stop();
        ExplosionTruck explosionTruck = new ExplosionTruck(truck, pane, game.getTrucks());
        game.getAnimations().add(explosionTruck);
        explosionTruck.play();
        User user = User.getLoggedInUser();
        user.setScore(user.getScore() + truck.getSCORE());
        this.stop();
        pane.getChildren().remove(bomb);
    }

    protected void killTanks(Tank tank) {
        game.setSuccessfulShoot(game.getSuccessfulShoot() + 1);
        tank.getTankShooting().stop();
        ExplosionTank explosionTank = new ExplosionTank(tank, pane, game.getTanks());
        game.getAnimations().add(explosionTank);
        explosionTank.play();
        User user = User.getLoggedInUser();
        user.setScore(user.getScore() + tank.getSCORE());
        this.stop();
        pane.getChildren().remove(bomb);
    }

    protected void killBomb(Bomb bomb) {
        ExplosionBomb explosionLand = new ExplosionBomb(bomb, pane, game.getBombs());
        game.getAnimations().add(explosionLand);
        explosionLand.play();
        game.getBombs().getChildren().remove(bomb);
        this.stop();
    }

    protected void killBuilding(Building building) {
        game.setSuccessfulShoot(game.getSuccessfulShoot() + 1);
        ExplosionBuilding explosionBuilding = new ExplosionBuilding(building, pane, game.getBuildings());
        game.getAnimations().add(explosionBuilding);
        explosionBuilding.play();
        User user = User.getLoggedInUser();
        user.setScore(user.getScore() + building.getSCORE());
        GameController.createNukeBombSymbol(pane, game, building);
        this.stop();
        pane.getChildren().remove(bomb);
    }

    protected void killBunkers(Bunker bunker) {
        game.setSuccessfulShoot(game.getSuccessfulShoot() + 1);
        ExplosionBunker explosionBunker = new ExplosionBunker(bunker, pane, game.getBunkers());
        game.getAnimations().add(explosionBunker);
        explosionBunker.play();
        User user = User.getLoggedInUser();
        user.setScore(user.getScore() + bunker.getSCORE());
        GameController.createClusterBombSymbol(pane, game, bunker);
        this.stop();
        pane.getChildren().remove(bomb);
    }

    protected void killTree(Tree tree) {
        ExplosionTree explosionTree = new ExplosionTree(tree, pane, game.getTrees());
        game.getAnimations().add(explosionTree);
        explosionTree.play();
        this.stop();
        pane.getChildren().remove(bomb);
    }

    protected void killZSU57(ZSU57 zsu57) {
        game.setSuccessfulShoot(game.getSuccessfulShoot() + 1);
        ExplosionZSU57 explosionZSU57 = new ExplosionZSU57(zsu57, game.getZsu57s(), pane);
        game.getAnimations().add(explosionZSU57);
        explosionZSU57.play();
        User user = User.getLoggedInUser();
        user.setScore(user.getScore() + zsu57.getSCORE());
        this.stop();
        pane.getChildren().remove(bomb);
        pane.getChildren().remove(zsu57.getBoundaryCircle());
    }

    @Override
    protected abstract void interpolate(double v);
}
