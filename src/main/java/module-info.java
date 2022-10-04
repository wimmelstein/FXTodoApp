module nl.inholland.fxtodoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens nl.inholland.fxtodoapp to javafx.fxml;
    exports nl.inholland.fxtodoapp;
}