package view.animations;

import javafx.scene.layout.Pane;
import model.*;

public class ZSU57BulletShooting extends BulletShooting {


    private final double x1;
    private final double y1;

    public ZSU57BulletShooting(Game game, Pane pane, ZSU57Bullet bullet, Plane plane, ZSU57 zsu57) {
        super(game, pane, bullet, plane, zsu57);
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
