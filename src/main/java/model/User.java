package model;

import controller.ApplicationController;
import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import view.GameAtomicBomber;
import view.LoginMenu;
import view.WinAndLossMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private  static ArrayList<User> allUsers=new ArrayList<>();
    private String userName;
    private String password;
    private static User loggedInUser;
    private String nameOfAvatar;
    private int score;
    private int numberOfNukeBomb;
    private int numberOfClusterBomb;
    private int numberOfIronBomb;
    private int numberOfPlanes;
    private boolean isGrayScale;
    private Level level;
    private transient MediaPlayer mediaPlayer;
    private String urlOfImage;
    private int id;
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        User.allUsers.add(this);
        Random random=new Random();
        nameOfAvatar="avatar"+random.nextInt(1,6);
        this.urlOfImage=User.class.getResource("/image/"+nameOfAvatar+".png").toExternalForm();
        this.isGrayScale=false;
        this.level=Level.EASY;
       numberOfIronBomb=1;
       numberOfClusterBomb=0;
       numberOfNukeBomb=0;
       numberOfPlanes=1;
       this.mediaPlayer= LoginMenu.getMediaPlayer();
        ArrayList<User> allUsers;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.ser"))) {
            allUsers = (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            allUsers = new ArrayList<>();
        }
        int id=allUsers.size()+User.allUsers.size();
    }
    public static void saveUserInformations(){
        ArrayList<User> allUsers;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.ser"))) {
            allUsers = (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            allUsers = new ArrayList<>();
        }
        User.allUsers.addAll(allUsers);
        try {
            for (User user : User.allUsers) {
                if (user.id==User.getLoggedInUser().id) {
                    user.setUrlOfImage(User.getLoggedInUser().urlOfImage);
                    user.setUserName(User.getLoggedInUser().getUserName());
                    user.setPassword(User.getLoggedInUser().getPassword());
                }
            }
        } catch (Exception e){}
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            out.writeObject(User.allUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }
    public static ArrayList<User> getAllUsers(){
        return allUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByUserName(String userName){
        for(User user:User.allUsers){
            if(user.getUserName().equals(userName)) return user;
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        GameAtomicBomber atomicBomber=GameAtomicBomber.getCurrentGameAtomicBomber();
        int wave=atomicBomber.getWave();
        if(wave==1 && score%19==0 && score!=0) {
            atomicBomber.setWave(2);
            atomicBomber.doWave2();
        }
        else if(wave==2 && score%62==0 && score!=0) atomicBomber.doWave3();
        else if(wave==3 && score%124==0 && score!=0) {
            try {
                new WinAndLossMenu(GameAtomicBomber.getCurrentGameAtomicBomber().getAccuracy()).start(ApplicationController.getStage());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public int getNumberOfNukeBomb() {
        return numberOfNukeBomb;
    }

    public void setNumberOfNukeBomb(int numberOfNukeBomb) {
        this.numberOfNukeBomb = numberOfNukeBomb;
    }

    public int getNumberOfClusterBomb() {
        return numberOfClusterBomb;
    }

    public void setNumberOfClusterBomb(int numberOfClusterBomb) {
        this.numberOfClusterBomb = numberOfClusterBomb;
    }

    public boolean isGrayScale() {
        return isGrayScale;
    }

    public void setGrayScale(boolean grayScale) {
        isGrayScale = grayScale;
    }
    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public int getNumberOfIronBomb() {
        return numberOfIronBomb;
    }

    public void setNumberOfIronBomb(int numberOfIronBomb) {
        this.numberOfIronBomb = numberOfIronBomb;
    }

    public int getNumberOfPlanes() {
        return numberOfPlanes;
    }

    public void setNumberOfPlanes(int numberOfPlanes) {
        this.numberOfPlanes = numberOfPlanes;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public String getUrlOfImage() {
        return urlOfImage;
    }

    public void setUrlOfImage(String urlOfImage) {
        this.urlOfImage=urlOfImage;
    }

}
