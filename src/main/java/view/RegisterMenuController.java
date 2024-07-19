package view;

import controller.ApplicationController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class RegisterMenuController {
    @FXML
    private TextField password;
    @FXML
    private TextField userName;

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

    public void register(MouseEvent mouseEvent) throws Exception {
        if (getUserByUserName(userName.getText()) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to register!");
            alert.setContentText("There is this username\nPlease enter another username");
            alert.setTitle("error");
            alert.show();
        } else {
            User user = new User(userName.getText(), password.getText());
            User.setLoggedInUser(user);
            new MainMenu().start(ApplicationController.getStage());
        }
    }

}
