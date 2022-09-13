module nl.inholland.fxtodoapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens nl.inholland.fxtodoapp to javafx.fxml;
    exports nl.inholland.fxtodoapp;
}