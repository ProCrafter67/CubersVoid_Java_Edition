module com.thriverstudios.cubersvoid {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires org.joml;
    requires org.lwjgl;
    requires org.lwjgl.ktx;
    requires org.lwjgl.stb;
    requires org.lwjgl.glfw;
    requires org.lwjgl.assimp;
    requires org.lwjgl.openal;
    requires org.lwjgl.opengl;

    opens com.thriverstudios.cubersvoid to javafx.fxml;
    exports com.thriverstudios.cubersvoid;
}