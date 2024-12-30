package com.thriverstudios.cubersvoid.game.modules.graphics;

import static org.lwjgl.opengl.GL30.*;

public class VertexBuffer {

    private int vao;
    private int vbo;

    public VertexBuffer(float[] data) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    public void SetupLayout(int layout_index, int size, int stride, int offset) {
        glVertexAttribPointer(layout_index, size, GL_FLOAT, false, stride * Float.BYTES, (long) offset * Float.BYTES);
        glEnableVertexAttribArray(layout_index);
    }

    public void Bind() {
        glBindVertexArray(vao);
    }

    public void Unbind() {
        glBindVertexArray(0);
    }

    public void Unbind_() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void Render(int mode, int offset, int count) {
        glDrawArrays(mode, offset, count);
    }

    public void Destroy() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
    }
}
