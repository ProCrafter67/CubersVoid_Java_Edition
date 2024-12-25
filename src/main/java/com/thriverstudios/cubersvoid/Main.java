package com.thriverstudios.cubersvoid;

import com.thriverstudios.cubersvoid.modules.objects.Quad;
import com.thriverstudios.cubersvoid.modules.objects.util.Renderer;
import com.thriverstudios.cubersvoid.modules.core.Window;

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