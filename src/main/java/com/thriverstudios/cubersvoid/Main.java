package com.thriverstudios.cubersvoid;

import com.thriverstudios.cubersvoid.launcher.ui.Home;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Home home = new Home(stage);

        stage.getIcons().add(new Image("https://i.pinimg.com/736x/98/03/31/98033112849e4c64bf7375a7620ae624.jpg"));

        stage.setTitle("CubersVoid");
        stage.setScene(home.getScene());
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}