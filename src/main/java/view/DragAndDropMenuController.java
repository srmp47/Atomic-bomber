package view;

import controller.ApplicationController;
import javafx.scene.input.MouseEvent;

public class DragAndDropMenuController {

    public void backToAvatarMenu(MouseEvent mouseEvent) throws Exception {
        new AvatarMenu().start(ApplicationController.getStage());
    }
}
