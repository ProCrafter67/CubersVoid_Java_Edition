package com.thriverstudios.cubersvoid.launcher;

public class Launcher {

    LauncherModule launcher;

    public Launcher() {
        launcher = new LauncherModule();
    }

    public void RunGame() {
        try {
            String[] command = {
                    "java",
                    "-cp",
                    System.getProperty("java.class.path"),
                    "src/../../src/main/java/com/thriverstudios/cubersvoid/game/Runner.java"
            };

            Process process = Runtime.getRuntime().exec(command);

            int exitCode = process.waitFor();
            System.out.println("Cuber's Void exited with exit code " + exitCode);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Run() {
        launcher.Launch();
    }
}
