import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    // The window handle
    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE); // the window will be maximized

        // Create the window
        window = glfwCreateWindow(800, 600, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.2f, 0.3f, 0.3f, 0.0f);

        CharSequence vertexShaderCode = "#version 330 core\n"
                + "layout (location = 0) in vec3 aPos;\n"
                + "layout (location = 1) in vec3 aColor;\n"
                + "out vec3 ourColor;\n"
                + "void main()\n"
                + "{\n"
                + "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n"
                + "    ourColor = aColor;\n"
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
                + "in vec3 ourColor;\n"
                + "out vec4 FragColor;\n"
                + "void main()\n"
                + "{\n"
                + "   FragColor = vec4(ourColor, 1.0f);\n"
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
                -0.5f,-0.5f, 0.0f, 1.0f, 0.0f, 0.0f,
                 0.5f,-0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
                 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f
        };

        int VAO = glGenVertexArrays();
        int VBO = glGenBuffers();

        glBindVertexArray(VAO);

        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glUseProgram(program);
            glBindVertexArray(VAO);
            glDrawArrays(GL_TRIANGLES, 0, 3);
            glBindVertexArray(0);

            glfwSwapBuffers(window); // swap the color buffer
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}