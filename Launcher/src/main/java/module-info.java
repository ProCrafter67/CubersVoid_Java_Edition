module com.thriverstudios.cubersvoid.launcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.thriverstudios.cubersvoid.launcher to javafx.fxml;
    exports com.thriverstudios.cubersvoid.launcher;
}