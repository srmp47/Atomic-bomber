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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.util.Objects;

public class AvatarMenu extends Application {
    public AvatarMenuController controller;

    private void changeColorOfGame(Stage stage) {
        ColorAdjust grayScale = new ColorAdjust();
        grayScale.setSaturation(-1);
        stage.getScene().getRoot().setEffect(grayScale);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        Button button = new Button("Choose Image");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                User.getLoggedInUser().setUrlOfImage(image.getUrl());
            }
        });
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = FXMLLoader.load(Objects.requireNonNull(AvatarMenu.class.getResource("/FXML/AvatarMenu.fxml")));
        this.controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        if (User.getLoggedInUser().isGrayScale()) changeColorOfGame(stage);
        button.setLayoutY(342);
        button.setLayoutX(300);
        pane.getChildren().add(button);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        pane.getChildren().add(imageView);

        // تنظیم EventHandler برای DragOver
        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        // تنظیم EventHandler برای DragDropped
        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                // فقط اولین فایل را بررسی می‌کنیم
                File file = db.getFiles().get(0);
                try {
                    Image img = new Image(file.toURI().toString());
                    imageView.setImage(img);
                    success = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
        Button button1 = new Button("Drag and Drop image");
        button1.setOnAction(e -> {
            try {
                new DragAndDropMenu().start(ApplicationController.getStage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        button1.setLayoutX(120);
        button1.setLayoutY(342);
        pane.getChildren().add(button1);
        stage.show();
    }
}


