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
}
