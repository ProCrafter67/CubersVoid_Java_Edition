package com.thriverstudios.cubersvoid.game.modules.objects.util;

import com.thriverstudios.cubersvoid.game.modules.graphics.Mesh;
import com.thriverstudios.cubersvoid.game.modules.graphics.Texture;
import com.thriverstudios.cubersvoid.game.modules.shader.Shader;

public abstract class Entity {
    private Mesh mesh;
    private Texture texture;
    private Shader shader;
    private int x, y, z;

    public abstract void Render();
    public abstract void Destroy();
}
