package com.thriverstudios.cubersvoid.game.modules.managers;

import static org.lwjgl.opengl.GL45.*;

public class GLErrorManager {

    public GLErrorManager() {}

    public void CheckGLError() {
        int error = glGetError();
        String message = "OpenGL Error : ";
        if (error != GL_NO_ERROR) {
            if( error == GL_INVALID_ENUM ) {
                System.err.println(message + "GL_INVALID_ENUM");
            } else if( error == GL_INVALID_VALUE ) {
                System.err.println(message + "GL_INVALID_VALUE");
            } else if( error == GL_INVALID_OPERATION ) {
                System.err.println(message + "GL_INVALID_OPERATION");
            }
        }
    }
}
