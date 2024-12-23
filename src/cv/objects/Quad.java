package cv.objects;

import cv.objects.util.Entity;
import cv.objects.util.Mesh;
import cv.shader.Shader;

public class Quad extends Entity {
    private Mesh mesh;
    private Shader shader;
    private int x, y, z;

    public Quad() {
        super();

        x = y = z = 0;

        float []vertices = {
                -0.5f,-0.5f, 0.0f,
                -0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                0.5f,-0.5f, 0.0f
        };

        int []indices = {
                0, 1, 2,
                0, 2, 3
        };

        shader = new Shader("src/cv/shaders/quad.vert", "src/cv/shaders/quad.frag");
        mesh = new Mesh(shader, vertices, indices);
    }

    public void Render() {
        mesh.Render(6, 0);
    }
}
