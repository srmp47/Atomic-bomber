module AtomicBomber2 {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    exports view;

    opens view to
            javafx.fxml;

}