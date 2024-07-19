package model;

import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import view.animations.MigShooting;
import view.animations.ZSU57Shooting;

import java.util.Objects;

public class Mig extends Rectangle {
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final Game GAME;
    private final int SCORE=10;
    private Circle boundaryCircle;
    private MigShooting migShooting;
    public double getRadius(){
        User user=User.getLoggedInUser();
        if(user.getLevel().equals(Level.EASY)) return 50;
        else if(user.getLevel().equals(Level.MEDIUM)) return 100;
        else if (user.getLevel().equals(Level.HARD)) return 150;
        else return 0;
    }
    public Mig(Game game){
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(0);
        Objects.requireNonNull(game);
        this.setY(100);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/mig1.png").toExternalForm())));
        boundaryCircle = new Circle(this.WIDTH / 2 + this.getRadius());
        boundaryCircle.setStroke(Color.RED);
        boundaryCircle.getStrokeDashArray().addAll(10d, 5d);
        boundaryCircle.setStrokeLineCap(StrokeLineCap.ROUND);
        boundaryCircle.setFill(Color.TRANSPARENT);
        boundaryCircle.setCenterX(this.getX() + this.WIDTH / 2);
        boundaryCircle.setCenterY(this.getY() + this.HEIGHT / 2);
        GAME.getPane().getChildren().add(boundaryCircle);
    }

    public MigShooting getMigShooting() {
        return migShooting;
    }

    public void setMigShooting(MigShooting migShooting) {
        this.migShooting = migShooting;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public int getSCORE() {
        return SCORE;
    }

    public Circle getBoundaryCircle() {
        return boundaryCircle;
    }
}
