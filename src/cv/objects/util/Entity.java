package cv.objects.util;

import cv.shader.Shader;

public abstract class Entity {
    private Mesh mesh;
    private Shader shader;
    private int x, y, z;

    public Entity() {
        x = y = z = 0;

        float []vertices = {};
        int []indices = {};
        mesh = new Mesh(shader, vertices, indices);
    }
    public abstract void Render();
}
