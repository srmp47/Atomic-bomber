package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.util.Objects;

public class PauseMenu extends Application {
    public PauseMenuController controller;

    private void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = FXMLLoader.load(Objects.requireNonNull(PauseMenu.class.getResource("/FXML/PauseMenu.fxml")));
        this.controller = fxmlLoader.getController();
        stage.setScene(new Scene(pane));
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        stage.show();
    }
}
