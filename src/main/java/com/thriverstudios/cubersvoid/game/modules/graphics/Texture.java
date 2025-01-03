package com.thriverstudios.cubersvoid.game.modules.graphics;

import org.lwjgl.BufferUtils;

import java.nio.*;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private String path;
    private int texture;
    private final int slot;

    public Texture(String path, int slot) {
        this.path = path;
        this.slot = slot;

        texture = glGenTextures();

        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, texture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width, height, nrChannels;
        width = BufferUtils.createIntBuffer(1);
        height = BufferUtils.createIntBuffer(1);
        nrChannels = BufferUtils.createIntBuffer(1);
        stbi_set_flip_vertically_on_load(true);

        ByteBuffer data = stbi_load(path, width, height, nrChannels, 0);

        if (data != null ) {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(), height.get(), 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        } else {
            System.out.println("Failed to load texture");
        }
        stbi_image_free(data);
    }

    public void Bind() {
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void Unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void Destroy() {
        glDeleteTextures(texture);
    }
}
