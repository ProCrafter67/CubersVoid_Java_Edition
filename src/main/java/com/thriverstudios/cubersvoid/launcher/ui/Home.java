package com.thriverstudios.cubersvoid.launcher.ui;

import com.thriverstudios.cubersvoid.game.CubersVoid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

public class Home {

    private final Scene scene;

    public Home(Stage stage) {
        Label label = new Label("Cuber's Void Launcher");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.GREEN);
        label.setPrefWidth(1920);
        label.setStyle("-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-style: italic;" +
                "-fx-font-family: 'Cascadia Code';" +
                "-fx-rotate: 0;"
        );

        Button button = new Button("Launch Cuber's Void");
        button.setOnAction(e -> {
            stage.close();
            CubersVoid game = new CubersVoid();
            game.Run();
        });
        button.setStyle("-fx-background-color: red;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-style: italic;" +
                "-fx-font-family: 'Cascadia Code';" +
                "-fx-rotate: 0;" +
                "-fx-scale-x: 100%;" +
                "-fx-scale-y: 100%;" +
                "-fx-scale-z: 100%;" +
                "-fx-opacity: 1.0;" +
                "-fx-cursor: wait;"
        );
        label.setLabelFor(button);

        BorderPane parent = new BorderPane();
        parent.setTop(label);

        GridPane grid = new GridPane();
        grid.setMinSize(800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.add(button, 0, 0);
        parent.setCenter(grid);

        parent.setPrefSize(800, 600);
        parent.setStyle("-fx-background-color: black;");

        scene = new Scene(parent);
    }

    public Scene getScene() {
        return scene;
    }
}