import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import cv.core.Window;

public class Main {

    // The window handle
    private Window window;

    public void run() {
        init();
        loop();

       window.Destroy();
    }

    private void init() {
        this.window = new Window();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        CharSequence vertexShaderCode = "#version 330 core\n"
                + "layout (location = 0) in vec3 aPos;\n"
                + "void main()\n"
                + "{\n"
                + "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
                + "}";

        int vertShader = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertShader, vertexShaderCode);
        glCompileShader(vertShader);

        IntBuffer success = BufferUtils.createIntBuffer(1024);
        CharSequence infoLog;
        glGetShaderiv(vertShader, GL_COMPILE_STATUS, success);

        if(success.get(0) == GL_FALSE) {
            infoLog = glGetShaderInfoLog(vertShader);
            throw new RuntimeException("Error creating vertex shader: " + infoLog);
        }

        CharSequence fragmentShaderCode = "#version 330 core\n"
                + "out vec4 FragColor;\n"
                + "void main()\n"
                + "{\n"
                + "   FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n"
                + "}";

        int fragShader = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(fragShader, fragmentShaderCode);
        glCompileShader(fragShader);

        glGetShaderiv(fragShader, GL_COMPILE_STATUS, success);

        if(success.get(0) == GL_FALSE) {
            infoLog = glGetShaderInfoLog(fragShader);
            throw new RuntimeException("Error creating fragment shader: " + infoLog);
        }

        int program = glCreateProgram();

        glAttachShader(program, vertShader);
        glAttachShader(program, fragShader);
        glLinkProgram(program);

        glGetProgramiv(program, GL_LINK_STATUS, success);

        if(success.get(0) == GL_FALSE) {
            infoLog = glGetProgramInfoLog(program);
            throw new RuntimeException("Error linking program: " + infoLog);
        }

        glUseProgram(program);

        float []vertices = {
                -0.5f,-0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,
                 0.5f, 0.5f, 0.0f,
                 0.5f,-0.5f, 0.0f
        };

        int []indices = {
                0, 1, 2,
                0, 2, 3
        };

        int VAO = glGenVertexArrays();
        int VBO = glGenBuffers();
        int EBO = glGenBuffers();

        glBindVertexArray(VAO);

        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

//        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); // To see the wireframe
        glEnable(GL_DEPTH_TEST);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( window.IsOpen() ) {
            {
                // Rendering
                glClearColor(0.2f, 0.3f, 0.3f, 0.0f);
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

                glUseProgram(program);
                glBindVertexArray(VAO);
                glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
                glBindVertexArray(0);
            }

            window.Update(); // swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}