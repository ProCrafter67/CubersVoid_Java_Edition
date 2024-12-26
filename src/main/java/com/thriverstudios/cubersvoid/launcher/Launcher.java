package com.thriverstudios.cubersvoid.launcher;

import com.thriverstudios.cubersvoid.game.CubersVoid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group(), 300, 200, Color.BEIGE);
        Group group = (Group) scene.getRoot();

        Button button = new Button("Play Cuber's Void");
        button.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Cuber's Void Launcher");
            alert.setContentText("Do you want to open Cuber's Void?");
            alert.showAndWait();

            alert.setOnHidden(onHidden -> {
                CubersVoid cubersVoid = new CubersVoid();
                cubersVoid.Run();
            });
        });

        stage.setTitle("CubersVoid");
        stage.setScene(scene);
        stage.show();
    }
}
