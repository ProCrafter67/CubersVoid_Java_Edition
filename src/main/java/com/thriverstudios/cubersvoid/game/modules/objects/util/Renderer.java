package com.thriverstudios.cubersvoid.game.modules.objects.util;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {
    private Entity []entities;

    public Renderer() {
        entities = new Entity[]{};
    }

    public Renderer(Entity entity) {
        entities = new Entity[]{entity};
    }

    public Renderer(Entity []nodes) {
        entities = nodes;
    }

    public void PushEntity(Entity entity) {
        entities[entities.length] = entity;
    }

    public void PopEntity() {
        entities[entities.length - 1] = null;
    }

    public void PopEntity(Entity entity) {
        for(int i = 0; i < entities.length; i++) {
            if(entities[i] == entity) {
                entities[i] = null;
            }
        }
    }

    public void Render() {
        SetWireframeMode(false);
        SetBGColor();
        RenderEntities();
    }

    public void Render(boolean wireframe) {
        SetWireframeMode(wireframe);
        SetBGColor();
        RenderEntities();
    }

    private void SetWireframeMode(boolean mode) {
        if(mode) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        } else {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
    }

    private void SetBGColor() {
        glClearColor(0.2f, 0.3f, 0.3f, 0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }

    public void RenderEntities() {
        for (Entity entity : entities) {
            if(entity != null)
                entity.Render();
        }
    }
}
