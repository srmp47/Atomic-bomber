package controller;

import java.util.Random;
import javafx.stage.Stage;

public class ApplicationController {
    private static Stage stage;
    public static final Random random = new Random();

    public ApplicationController() {
    }

    public static void setStage(Stage stage) {
        ApplicationController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}
