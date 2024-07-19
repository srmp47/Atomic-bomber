package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class MainMenu extends Application {
    MainMenuController controller;

    private void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainMenu.fxml"));
        Pane pane = fxmlLoader.load();
        Image image = new Image(User.getLoggedInUser().getUrlOfImage());
        ImageView imageView = new ImageView(image);
        imageView.setX(199);
        imageView.setY(41);
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);
        this.controller = fxmlLoader.getController();
        this.controller.setUserName(User.getLoggedInUser().getUserName());
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        pane.getChildren().add(imageView);
        imageView.setX(199);
        imageView.setY(41);
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);
        stage.show();
    }


}
