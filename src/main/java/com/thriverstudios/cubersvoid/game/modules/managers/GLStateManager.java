package com.thriverstudios.cubersvoid.game.modules.managers;

import java.util.concurrent.Callable;

import static org.lwjgl.opengl.GL11.*;

public class GLStateManager {

    public GLStateManager() {}

    public void Enable(int capability) {
        glEnable(capability);
    }

    public void Disable(int capability) {
        glDisable(capability);
    }

    private void Run(Callable<Void> callable, int capability) {
        try {
            if(glIsEnabled(capability)) {
                callable.call();
            } else {
                throw new Exception("Cannot call Function");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
