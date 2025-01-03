package com.thriverstudios.cubersvoid;

import com.thriverstudios.cubersvoid.launcher.ui.Home;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Home home = new Home(stage);

        stage.getIcons().add(
                new Image(
                        "https://raw.githubusercontent.com/ProCrafter67/CubersVoid_Java_Edition/refs/heads/master/src/main/resources/com/icon.jpg?token=GHSAT0AAAAAAC32HUDEHM57XWJ53NN26J3CZ3PFBGQ"
                )
        );

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