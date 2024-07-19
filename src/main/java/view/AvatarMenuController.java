package view;

import controller.ApplicationController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;

public class AvatarMenuController {

    public void changeAvatar(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Avatar changed");
        alert.setHeaderText("Done successfully");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ImageView imageView = (ImageView) mouseEvent.getSource();
                User.getLoggedInUser().setUrlOfImage(imageView.getImage().getUrl());
            }
        });
    }

    public void goToMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(ApplicationController.getStage());
    }

    public void goToDragAndDropMenu(MouseEvent mouseEvent) throws Exception {
        new DragAndDropMenu().start(ApplicationController.getStage());
    }
}
