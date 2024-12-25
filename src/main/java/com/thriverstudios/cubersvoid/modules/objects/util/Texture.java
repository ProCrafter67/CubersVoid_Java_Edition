package com.thriverstudios.cubersvoid.modules.objects.util;

import org.lwjgl.BufferUtils;

import java.nio.*;

//import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private CharSequence path;
    private int texture;

    public Texture(String path) {
        this.path = path;

        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width, height, nrChannels;
        width = BufferUtils.createIntBuffer(1024);
        height = BufferUtils.createIntBuffer(1024);
        nrChannels = BufferUtils.createIntBuffer(1024);

        stbi_set_flip_vertically_on_load(true);

        ByteBuffer data = stbi_load(this.path, width, height, nrChannels, 0);

        if(data != null) {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0), 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        } else {
            System.err.println("Failed to load texture");
        }
        assert data != null;
        stbi_image_free(data);
    }

    public void Use(int slot) {
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void Unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
