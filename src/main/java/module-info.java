module nl.inholland.fxtodoapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens nl.inholland.fxtodoapp to javafx.fxml;
    exports nl.inholland.fxtodoapp;
}