package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ClusterBombSymbol;
import model.Game;
import model.User;

public class ClusterBombSymbolShooting extends Transition {
    private final double X_SPEED = 0;
    private final int DURATION = 300;
    private final double Y_SPEED = 2;
    private final double GHANGING_Y = 0.4;
    private final Game game;
    private final Pane pane;
    private final ClusterBombSymbol clusterBombSymbol;


    public ClusterBombSymbolShooting(Game game, Pane pane, ClusterBombSymbol clusterBombSymbol) {
        this.pane = pane;
        this.game = game;
        this.clusterBombSymbol = clusterBombSymbol;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        clusterBombSymbol.setY(clusterBombSymbol.getY() - GHANGING_Y);
        if (clusterBombSymbol.getBoundsInParent().intersects(game.getPlane().getBoundsInParent())) {
            User user = User.getLoggedInUser();
            user.setNumberOfClusterBomb(user.getNumberOfClusterBomb() + 1);
            clusterBombSymbol.getClusterBombSymbolShooting().stop();
            pane.getChildren().remove(clusterBombSymbol);
            game.updateLabelOfCluster();
            this.stop();
        }
    }
}
