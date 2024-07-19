package view;

import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.User;

public class PauseMenuController {
    public void mute(MouseEvent mouseEvent) {
        User.getLoggedInUser().getMediaPlayer().setMute(true);
    }

    public void play(MouseEvent mouseEvent) {
        User.getLoggedInUser().getMediaPlayer().setMute(false);
    }

    public void playGameMusic1(MouseEvent mouseEvent) {
        User.getLoggedInUser().getMediaPlayer().setMute(true);
        Media media = new Media(PauseMenu.class.getResource("/media/gameMusic1.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        User.getLoggedInUser().setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void playGameMusic2(MouseEvent mouseEvent) {
        Media media = new Media(PauseMenu.class.getResource("/media/gameMusic2.mp3").toString());
        User.getLoggedInUser().getMediaPlayer().setMute(true);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        User.getLoggedInUser().setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void playGameMusic3(MouseEvent mouseEvent) {
        Media media = new Media(PauseMenu.class.getResource("/media/gameMusic3.mp3").toString());
        User.getLoggedInUser().getMediaPlayer().setMute(true);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        User.getLoggedInUser().setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }
}
