package com.thriverstudios.cubersvoid.launcher.handler;

import com.thriverstudios.cubersvoid.core.Logger;
import javafx.scene.control.Alert;

import java.io.File;

public class ErrorHandler {

    public static void CreateErrorMessage(String error_message) {
        ReportErrorLog(error_message);
        EnactReporterUI();
    }

    private static void ReportErrorLog(String error_message) {
        Logger.LogError(error_message);
    }

    private static void EnactReporterUI() {
        Alert message_box = new Alert(Alert.AlertType.ERROR);
        message_box.setHeaderText(null);
        message_box.setTitle("Cuber's Void Launcher");
        message_box.setContentText("Do you want to open Cuber's Void?");
        message_box.showAndWait();
    }
}
