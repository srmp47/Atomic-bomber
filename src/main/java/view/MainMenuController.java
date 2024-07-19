package view;

import controller.ApplicationController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.User;

public class MainMenuController {
    @FXML
    private Text textOfUserName;
    @FXML
    private ImageView avatar;

    public void setUserName(String userName) {
        textOfUserName.setText(userName);
    }
//    public void setAvatar(String avatarName){
//        if(!avatarName.equals("defaultAvatar")) {
//            File file = new File("C:/Users/b.r/Desktop/AP/AtomicBomber/src/main/classes/image/" + avatarName + ".png");
//            String imageUrl = file.toURI().toString();
//            Image newImage = new Image(imageUrl);
//            avatar.setImage(newImage);
//        }
//    }


    public void goToProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(ApplicationController.getStage());
    }

    public void seeRanking(MouseEvent mouseEvent) {
    }

    public void startNewGame(MouseEvent mouseEvent) throws Exception {
        new GameAtomicBomber(User.getLoggedInUser().getUserName()).start(ApplicationController.getStage());
    }

    public void goToSettings(MouseEvent mouseEvent) throws Exception {
        new Settings().start(ApplicationController.getStage());
    }
}
