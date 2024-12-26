package com.thriverstudios.cubersvoid.launcher;

import javafx.application.Application;
import javafx.stage.Stage;

import com.thriverstudios.cubersvoid.launcher.ui.Home;

public class Launcher extends Application{
    @Override
    public void start(Stage stage) {
        stage.setTitle("Cubers Void");

        Home home = new Home();
        stage.setScene(home);
    }

    public void run() {
        Application.launch();
    }
}
