package view;

import controller.ApplicationController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginMenuController {
    private static LoginMenuController loginMenuController;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TilePane TilePane;

    public LoginMenuController() {
        loginMenuController = this;
    }

    public static LoginMenuController getCurrentLoginMenuController() {
        return loginMenuController;
    }

    public void goToRegisterMenu(MouseEvent keyEvent) throws Exception {
        new RegisterMenu().start(ApplicationController.getStage());
    }

    private User getUserByUserName(String userName) {
        ArrayList<User> allUsers;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.ser"))) {
            allUsers = (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            allUsers = new ArrayList<>();
        }
        for (User user : allUsers) {
            if (user.getUserName().equals(userName)) return user;
        }
        return null;

    }

    public void login(MouseEvent keyEvent) throws Exception {
        if (getUserByUserName(userName.getText()) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is not this username");
            alert.setHeaderText("Unable to login!");
            alert.setTitle("error");
            alert.show();
            userName.setText("");
            password.setText("");
        } else if (!getUserByUserName(userName.getText()).getPassword().equals(password.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong password");
            alert.setTitle("error");
            alert.setHeaderText("Unable to login!");
            alert.show();
            userName.setText("");
            password.setText("");
        } else {
            User.setLoggedInUser(getUserByUserName(userName.getText()));
            new MainMenu().start(ApplicationController.getStage());
        }
    }

    public void loginAsGuest(MouseEvent keyEvent) throws Exception {
        User user = new User("", "");
        User.setLoggedInUser(user);
        new GameAtomicBomber("").start(ApplicationController.getStage());
    }
}
