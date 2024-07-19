package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.User;

import java.util.Objects;

public class WinAndLossMenu extends Application {
    private final double accuracy;

    public WinAndLossMenu(double accuracy) {
        this.accuracy = accuracy;
    }

    private void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        User user = User.getLoggedInUser();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = FXMLLoader.load(Objects.requireNonNull(ProfileMenu.class.getResource("/FXML/WinAndLossMenu.fxml")));
        stage.setScene(new Scene(pane));
        Text textOfWinOrLoss = new Text();
        if (user.getScore() % 124 == 0 && user.getScore() != 0) textOfWinOrLoss.setText("You Win");
        else textOfWinOrLoss.setText("You Loss");
        textOfWinOrLoss.setX(pane.getWidth() / 2 - 40);
        textOfWinOrLoss.setY(50);
        textOfWinOrLoss.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(textOfWinOrLoss);
        Text textOfKills = new Text("Kills : " + user.getScore());
        textOfKills.setX(pane.getWidth() / 2 - 40);
        textOfKills.setY(100);
        textOfKills.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(textOfKills);
        Text textOfAccuracy = new Text("Accuracy : " + accuracy);
        textOfAccuracy.setX(pane.getWidth() / 2 - 40);
        textOfAccuracy.setY(150);
        textOfAccuracy.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(textOfAccuracy);
        if (!user.getUserName().equals("")) {
            Button button = new Button("Back to main menu");
            button.setLayoutY(200);
            button.setLayoutX(pane.getWidth() / 2 - 40);
            pane.getChildren().add(button);
            button.setOnAction(e -> {
                try {
                    new MainMenu().start(ApplicationController.getStage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        stage.show();
    }
}
