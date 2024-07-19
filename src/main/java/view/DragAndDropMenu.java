package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.util.Objects;

public class DragAndDropMenu extends Application {
    public DragAndDropMenuController controller;

    private void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    private void getImageFromUser(Pane pane, ImageView imageView) {
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setX(pane.getWidth() / 2 - imageView.getFitWidth() / 2);
        imageView.setY(pane.getHeight() / 2 - imageView.getFitHeight() / 2);
        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File file = db.getFiles().get(0);
                try {
                    Image img = new Image(file.toURI().toString());
                    imageView.setImage(img);
                    User.getLoggedInUser().setUrlOfImage(img.getUrl());
                    success = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void createButtonOfBack(Pane pane) {
        Button button = new Button("Back");
        button.setLayoutX(pane.getHeight() / 2);
        button.setLayoutY(pane.getHeight() - 50);
        button.setOnAction(e -> {
            try {
                new AvatarMenu().start(ApplicationController.getStage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        pane.getChildren().add(button);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = FXMLLoader.load(Objects.requireNonNull(AvatarMenu.class.getResource("/FXML/DragAndDropMenu.fxml")));
        this.controller = fxmlLoader.getController();
        stage.setScene(new Scene(pane));
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        ImageView imageView = new ImageView();
        pane.getChildren().add(imageView);
        getImageFromUser(pane, imageView);

        stage.show();
    }
}
