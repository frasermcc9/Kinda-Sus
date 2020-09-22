module com.fraserco {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires jnativehook;
    requires java.logging;

    opens com.fraserco to javafx.fxml, javafx.graphics;
    opens com.fraserco.controllers to javafx.fxml, javafx.graphics;
    exports com.fraserco.controllers;
}