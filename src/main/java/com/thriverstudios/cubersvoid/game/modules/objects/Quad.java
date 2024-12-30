package com.thriverstudios.cubersvoid.game.modules.objects;

import com.thriverstudios.cubersvoid.game.modules.objects.util.Entity;
import com.thriverstudios.cubersvoid.game.modules.graphics.Mesh;
import com.thriverstudios.cubersvoid.game.modules.graphics.Texture;
import com.thriverstudios.cubersvoid.game.modules.shader.Shader;

public class Quad extends Entity {
    private Mesh mesh;
    private Texture texture;
    private Shader shader;
    private int x, y, z;

    public Quad() {

        x = y = z = 0;

        float []vertices = {
                -0.5f,-0.5f, 0.0f,  0.0f, 0.0f,
                -0.5f, 0.5f, 0.0f,  0.0f, 1.0f,
                 0.5f, 0.5f, 0.0f,  1.0f, 1.0f,

                 0.5f, 0.5f, 0.0f,  1.0f, 1.0f,
                 0.5f,-0.5f, 0.0f,  1.0f, 0.0f,
                -0.5f,-0.5f, 0.0f,  0.0f, 0.0f,
        };

        shader = new Shader(
                "src/main/java/com/thriverstudios/cubersvoid/game/modules/shaders/quad.vert",
                "src/main/java/com/thriverstudios/cubersvoid/game/modules/shaders/quad.frag"
        );
        mesh = new Mesh(vertices);
        texture = new Texture("src/../assets/cubers_void/textures/block/Default.png", 0);

        shader.Use();
        shader.PutInt("uTexture", 0);
    }

    public void Render() {
        shader.Use();
        texture.Bind();
        mesh.Render(6, 0);
        texture.Unbind();
    }

    public void Destroy() {
        mesh.Destroy();
        texture.Destroy();
        shader.Delete();
    }
}
