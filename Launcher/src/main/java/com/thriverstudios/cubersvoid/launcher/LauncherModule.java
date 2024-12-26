package com.thriverstudios.cubersvoid.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LauncherModule extends Application {

    private Scene scene;

    public LauncherModule() {
        super();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LauncherModule.class.getResource("hello-view.fxml"));
        this.scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void Launch() {
        Application.launch();
    }
}