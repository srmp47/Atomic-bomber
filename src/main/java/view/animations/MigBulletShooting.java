package view.animations;

import javafx.scene.layout.Pane;
import model.*;

public class MigBulletShooting extends BulletShooting {
    private final double x1;
    private final double y1;

    public MigBulletShooting(Game game, Pane pane, MigBullet bullet, Plane plane, Mig mig) {
        super(game, pane, bullet, plane, mig);
        x1 = plane.getX();
        y1 = plane.getY();
        bullet.setRotate(getRotateBetweenPlaneAndBullet(x1, y1, bullet));

    }


    @Override
    protected void interpolate(double v) {
        double rotate = getRotateBetweenPlaneAndBullet(x1, y1, bullet);
        bullet.setX(bullet.getX() + SPEED * Math.cos(rotate));
        bullet.setY(bullet.getY() + SPEED * Math.sin(rotate));
        if (bullet.getBoundsInParent().intersects(plane.getBoundsInParent())) {

            game.getBullets().getChildren().remove(bullet);
            if (User.getLoggedInUser().getNumberOfPlanes() > 0) {
                User.getLoggedInUser().setNumberOfPlanes(User.getLoggedInUser().getNumberOfPlanes() - 1);
                game.updateLabelOfPlanes();
                plane.setX(0);
                plane.setY(70);
            } else if (!plane.isHit()) {
                this.stop();
                System.out.println("losed:(");
                plane.getPlaneShooting().stop();
                pane.getChildren().remove(plane);
                plane.isHit();
            }

        }
    }
}
