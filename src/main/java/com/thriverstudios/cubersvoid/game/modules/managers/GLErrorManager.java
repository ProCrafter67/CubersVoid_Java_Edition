package com.thriverstudios.cubersvoid.game.modules.managers;

import com.thriverstudios.cubersvoid.core.Logger;
import static org.lwjgl.opengl.GL45.*;
import java.util.Random;

public class GLErrorManager {

    public GLErrorManager() {}

    public void CheckGLError() {
        int error = glGetError();
        String message = "OpenGL Error : ";
        if (error != GL_NO_ERROR) {
            if( error == GL_INVALID_ENUM ) {
                String cache_name = "OpenGL_Error" + new Random().nextLong(1000000000L);
                Logger.LogError(cache_name, message + ";OpenGL error found : GL_INVALID_ENUM");
            } else if( error == GL_INVALID_VALUE ) {
                String cache_name = "OpenGL_Error" + new Random().nextLong(1000000000L);
                Logger.LogError(cache_name, message + ";OpenGL error found : GL_INVALID_VALUE");
            } else if( error == GL_INVALID_OPERATION ) {
                String cache_name = "OpenGL_Error" + new Random().nextLong(1000000000L);
                Logger.LogError(cache_name, message + ";OpenGL error found : GL_INVALID_OPERATION");
            }
        }
    }
}
