package view;

import controller.ApplicationController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.User;

public class LoginMenu extends Application {

    private static MediaPlayer mediaPlayer;
    private LoginMenuController controller;

    public static void main(String[] args) {
        launch(args);
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    private void playGameMusic() {
        Media media = new Media(User.class.getResource
                ("/media/gameMusic2.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.setAutoPlay(true);
        LoginMenu.mediaPlayer = mediaPlayer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("C:/Users/b.r/Desktop/AP/AtomicBomber2/src/main/java/image/icon2.png");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.centerOnScreen();
        ApplicationController.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = FXMLLoader.load(getClass().getResource("/FXML/LoginMenu.fxml"));
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                User.saveUserInformations();
            }
        });
        this.controller = fxmlLoader.getController();
        stage.setScene(new Scene(pane));
        if (LoginMenu.mediaPlayer == null) playGameMusic();
        stage.show();
    }
}