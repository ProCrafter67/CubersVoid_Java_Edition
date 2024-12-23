import cv.objects.Quad;
import cv.objects.util.Renderer;
import org.lwjgl.opengl.*;

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

        Quad quad = new Quad();
        Renderer renderer = new Renderer(quad);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( window.IsOpen() ) {
            // Rendering
            renderer.Render();

            window.Update(); // swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}