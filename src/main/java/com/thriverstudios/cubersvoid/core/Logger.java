package com.thriverstudios.cubersvoid.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static final String logs_folder_path = "src/../reports/dump/errors";

    public static void LogError(String cache_name, String error) {
        LogConsole(error);
        CreateLogReport(error);
        CreateErrorCache(cache_name, error);
    }

    private static void LogConsole(String error) {
        System.err.println(error);
    }

    private static void CreateLogReport(String error) {
        try(FileWriter logger = new FileWriter(logs_folder_path + "/errors.record", true)) {
            logger.write("[ERROR] : " + error + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void CreateErrorCache(String cache_name, String error) {
        try {
            File file = new File(logs_folder_path + "/cache/" + cache_name +".txt");
            file.createNewFile();

            FileWriter logger = new FileWriter(logs_folder_path + "/cache/" + cache_name +".txt", true);
            logger.write("[ERROR_REPORT] : " + error + "\n");
            logger.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
