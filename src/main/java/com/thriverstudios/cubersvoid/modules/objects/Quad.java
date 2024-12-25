package com.thriverstudios.cubersvoid.modules.objects;

import com.thriverstudios.cubersvoid.modules.objects.util.Entity;
import com.thriverstudios.cubersvoid.modules.objects.util.Mesh;
import com.thriverstudios.cubersvoid.modules.objects.util.Texture;
import com.thriverstudios.cubersvoid.modules.shader.Shader;

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
                 0.5f,-0.5f, 0.0f,  1.0f, 0.0f
        };

        int []indices = {
                0, 1, 2,
                0, 2, 3
        };

        shader = new Shader("src/cv/shaders/quad.vert", "src/cv/shaders/quad.frag");
        texture = new Texture("src/cv/textures/Cobblestone.png");
        mesh = new Mesh(shader, texture, vertices, indices);
    }

    public void Render() {
        mesh.Render(6, 0);
    }
}
