package com.thriverstudios.cubersvoid.modules.objects.util;

import com.thriverstudios.cubersvoid.modules.shader.Shader;

public abstract class Entity {
    private Mesh mesh;
    private Texture texture;
    private Shader shader;
    private int x, y, z;

    public abstract void Render();
}
