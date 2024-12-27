package com.thriverstudios.cubersvoid.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    public static void LogError(String error) {
        LogConsole(error);
        CreateLogReport(error);
    }

    private static void LogConsole(String error) {
        System.err.println(error);
    }

    private static void CreateLogReport(String error) {
        try(FileWriter logger = new FileWriter(new File("src/../reports/dump/errors/errors.record"))) {
            logger.write("[ERROR] : " + error + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
