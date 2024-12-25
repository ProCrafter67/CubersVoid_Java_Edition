package com.thriverstudios.cubersvoid.modules.objects.util;

import com.thriverstudios.cubersvoid.modules.shader.Shader;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private Shader shader;
    private Texture texture;
    private int VAO, VBO, EBO;

    public Mesh(Shader shader, Texture texture, float[] data, int[] indices) {
        this.shader = shader;
        this.texture = texture;

        VAO = glGenVertexArrays();
        VBO = glGenBuffers();
        EBO = glGenBuffers();

        glBindVertexArray(VAO);

        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        shader.PutInt("uTexture", 0);
//        texture.Use(0);
    }

    public void Render(int count, int offset) {
        texture.Use(0);
        shader.Use();
        glBindVertexArray(VAO);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, offset);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        texture.Unbind();
    }
}
