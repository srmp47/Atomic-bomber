package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class Settings extends Application {
    private static Settings currentSettings;
    SettingsController controller;

    public Settings() {
        Settings.currentSettings = this;
    }

    public static Settings getCurrentSettings() {
        return currentSettings;
    }

    public void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Settings.fxml"));
        Pane pane = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        stage.show();
    }
}
