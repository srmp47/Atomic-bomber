package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.NukeBombSymbol;
import model.User;

public class NukeBombSymbolShooting extends Transition {
    private final double X_SPEED = 0;
    private final int DURATION = 300;
    private final double Y_SPEED = 2;
    private final double GHANGING_Y = 0.4;
    private final Game game;
    private final Pane pane;
    private final NukeBombSymbol nukeBombSymbol;


    public NukeBombSymbolShooting(Game game, Pane pane, NukeBombSymbol nukeBombSymbol) {
        this.pane = pane;
        this.game = game;
        this.nukeBombSymbol = nukeBombSymbol;
        this.setCycleDuration(Duration.millis(DURATION));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        nukeBombSymbol.setY(nukeBombSymbol.getY() - GHANGING_Y);
        if (nukeBombSymbol.getBoundsInParent().intersects(game.getPlane().getBoundsInParent())) {
            User user = User.getLoggedInUser();
            user.setNumberOfNukeBomb(user.getNumberOfNukeBomb() + 1);
            game.updateLabelOfNukeBomb();
            nukeBombSymbol.getNukeBombSymbolShooting().stop();
            pane.getChildren().remove(nukeBombSymbol);
            this.stop();
        }
    }

}
