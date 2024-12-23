package cv.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long window;

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 600;
    private final String DEFAULT_TITLE = "Cuber's Void";

    public Window() {
        glfwInit();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        window = glfwCreateWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE, NULL, NULL);

        if(window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                this.Close(); // We will detect this in the rendering loop
        });
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    public boolean IsOpen() {
        return !glfwWindowShouldClose(window);
    }

    public void Update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void Destroy() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
    public void Close() {
        glfwSetWindowShouldClose(window, true);
    }
}
