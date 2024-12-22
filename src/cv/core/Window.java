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

        window = glfwCreateWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE, NULL, NULL);
    }
}
