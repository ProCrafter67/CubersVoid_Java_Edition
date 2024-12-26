package com.thriverstudios.cubersvoid.game;

import com.thriverstudios.cubersvoid.game.modules.core.Window;
import com.thriverstudios.cubersvoid.game.modules.managers.GLErrorManager;
import com.thriverstudios.cubersvoid.game.modules.objects.Quad;
import com.thriverstudios.cubersvoid.game.modules.objects.util.Renderer;

public class CubersVoid {

    private Window window;

    public CubersVoid() {
        init();
    }

    public void Run() {
        loop();
        window.Destroy();
    }

    private void init() {
        this.window = new Window();
    }

    private void loop() {
        GLErrorManager debugger = new GLErrorManager();

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

}
