package view;

import controller.ApplicationController;
import enums.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import model.User;

public class SettingsController {

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(ApplicationController.getStage());
    }

    public void muteMusic(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("The music muted");
        alert.setHeaderText("Done successfully");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().getMediaPlayer().setMute(true);
            }
        });
    }

    public void changeColorToGray(MouseEvent mouseEvent) {
        User.getLoggedInUser().setGrayScale(true);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("The color changed");
        alert.setHeaderText("Done successfully");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().setGrayScale(true);
                Settings.getCurrentSettings().changeColorOfGame(ApplicationController.getStage());
            }
        });
    }

    public void convertLevelToHard(MouseEvent mouseEvent) {
        User.getLoggedInUser().setLevel(Level.HARD);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Done successfully");
        alert.setContentText("Level of game is : Hard");
        alert.setTitle("Confirmation");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().setLevel(Level.HARD);
            }
        });
    }

    public void convertLevelToEasy(MouseEvent mouseEvent) {
        User.getLoggedInUser().setLevel(Level.EASY);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Done successfully");
        alert.setContentText("Level of game is : Easy");
        alert.setTitle("Confirmation");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().setLevel(Level.EASY);
            }
        });

    }

    public void convertLevelToMedium(MouseEvent mouseEvent) {
        User.getLoggedInUser().setLevel(Level.MEDIUM);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Done successfully");
        alert.setContentText("Level of game is : Medium");
        alert.setTitle("Confirmation");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().setLevel(Level.MEDIUM);
            }
        });
    }

    public void unmuteMusic(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("The music unmuted");
        alert.setHeaderText("Done successfully");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                User.getLoggedInUser().getMediaPlayer().setMute(false);
            }
        });
    }
}
