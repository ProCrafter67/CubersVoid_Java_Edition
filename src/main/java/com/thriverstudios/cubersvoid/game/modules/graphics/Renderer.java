package com.thriverstudios.cubersvoid.game.modules.graphics;

import com.thriverstudios.cubersvoid.game.modules.core.Scene;
import com.thriverstudios.cubersvoid.game.modules.objects.util.Entity;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {
    private Scene scene;

    public Renderer(Scene scene) {
        this.scene = scene;
    }

    public void Render(Vector3f color) {
        SetWireframeMode(false);
        ClearColor(color);
        RenderEntities();
    }

    public void Render(boolean wireframe, Vector3f color) {
        SetWireframeMode(wireframe);
        ClearColor(color);
        RenderEntities();
    }

    private void SetWireframeMode(boolean mode) {
        if(mode) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        } else {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
    }

    private void ClearColor(Vector3f color) {
        glClearColor(color.x, color.y, color.z, 0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }

    public void RenderEntities() {
        for (Entity entity : scene.GetEntities()) {
            if(entity != null)
                entity.Render();
        }
    }

    public void CleanUp() {
        for (Entity entity : scene.GetEntities()) {
            if(entity != null)
                entity.Destroy();
        }
    }

    public void SetScene(Scene scene) {
        this.scene = scene;
    }
}
