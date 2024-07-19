package view;

import controller.ApplicationController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

public class ProfileMenuController {
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newPassword;

    public void updateUserName(MouseEvent mouseEvent) {
        if (newUsername.getText().equals(User.getLoggedInUser().getUserName())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("This username is the same as your last username");
            alert.setHeaderText("Unable to update username!");
            alert.show();
            newUsername.setText("");
        } else if (User.getUserByUserName(newUsername.getText()) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("Unable to update username!");
            alert.setContentText("There is this username\nPlease enter another username");
            alert.show();
            newUsername.setText("");
        } else if (newUsername.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to change username!");
            alert.setTitle("error");
            alert.setContentText("please enter a username");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Username changed\nYour new username is : " + newUsername.getText());
            alert.setHeaderText("Done successfully");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    User.getLoggedInUser().setUserName(newUsername.getText());
                    newUsername.setText("");
                }
            });

        }
    }

    public void updatePassword(MouseEvent mouseEvent) {
        if (newPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to change password!");
            alert.setTitle("error");
            alert.setContentText("please enter a password");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Password changed\nYour new Password is : " + newPassword.getText());
            alert.setHeaderText("Done successfully");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    User.getLoggedInUser().setPassword(newPassword.getText());
                    newPassword.setText("");
                }
            });
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        User.getAllUsers().remove(User.getLoggedInUser());
        new LoginMenu().start(ApplicationController.getStage());
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(ApplicationController.getStage());
    }

    public void goToAvatarMenu(MouseEvent mouseEvent) throws Exception {
        new AvatarMenu().start(ApplicationController.getStage());
    }
}
