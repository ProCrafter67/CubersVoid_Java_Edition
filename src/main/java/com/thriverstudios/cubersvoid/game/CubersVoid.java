package com.thriverstudios.cubersvoid.game;

import com.thriverstudios.cubersvoid.game.modules.core.Scene;
import com.thriverstudios.cubersvoid.game.modules.core.Window;
import com.thriverstudios.cubersvoid.game.modules.managers.GLErrorManager;
import com.thriverstudios.cubersvoid.game.modules.objects.Quad;
import com.thriverstudios.cubersvoid.game.modules.graphics.Renderer;
import org.joml.Vector3f;

public class CubersVoid {

    private Window window;
    private GLErrorManager debugger;
    private Scene scene;

    public CubersVoid() {
        init();
    }

    public void Run() {
        loop();
    }

    private void init() {
        this.window = new Window();
    }

    private void loop() {
        debugger = new GLErrorManager();
        SetupGameEntities();
        Renderer renderer = new Renderer(scene);

        while ( window.IsOpen() ) {
            renderer.Render(new Vector3f(0.2f, 0.3f, 0.3f));
            debugger.CheckGLError();

            window.Update();
        }
        renderer.CleanUp();
        window.Destroy();
    }

    private void SetupGameEntities() {
        scene = new Scene();

        Quad quad = new Quad();
        scene.AddEntity(quad);
        debugger.CheckGLError();
    }
}
