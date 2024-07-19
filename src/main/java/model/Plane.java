package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.animations.PlaneShooting;

import java.util.Objects;

public class Plane extends Rectangle {
    private final double X =70;
    private final double Y =70;
    private final double WIDTH=40;
    private final double HEIGHT=20;
    private final double SPEED=1;
    private PlaneShooting planeShooting;
    private final Game GAME;
    private  boolean isHit;
    public Plane(Game game) {
        super(40,20);
        this.GAME = game;
        Objects.requireNonNull(game);
        this.setX(X);
        Objects.requireNonNull(game);
        this.setY(Y);
        this.setFill(new ImagePattern(new Image(Plane.class.getResource("/image/plane.png").toExternalForm())));
    }

    public double getWIDTH() {
        return WIDTH;
    }
    public double getHEIGHT(){
        return HEIGHT;
    }

    public double getSPEED() {
        return SPEED;
    }

    public PlaneShooting getPlaneShooting() {
        return planeShooting;
    }

    public void setPlaneShooting(PlaneShooting planeShooting) {
        this.planeShooting = planeShooting;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
