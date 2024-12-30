package com.thriverstudios.cubersvoid.game.modules.graphics;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final VertexBuffer vertex_buffer;

    public Mesh(float[] data) {
        vertex_buffer = new VertexBuffer(data);
        vertex_buffer.SetupLayout(0, 3, 5, 0);
        vertex_buffer.SetupLayout(1, 2, 5, 3);
        vertex_buffer.Unbind_();
    }

    public void Render(int count, int offset) {
        vertex_buffer.Bind();
        vertex_buffer.Render(GL_TRIANGLES, offset, count);
        vertex_buffer.Unbind();
    }

    public void Destroy() {
        vertex_buffer.Destroy();
    }
}
