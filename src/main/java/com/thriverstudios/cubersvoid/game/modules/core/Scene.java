package com.thriverstudios.cubersvoid.game.modules.core;

import com.thriverstudios.cubersvoid.game.modules.objects.util.Entity;

public class Scene {

    private Entity[] entities;

    public Scene() {
        entities = new Entity[]{};
    }

    public Scene(Entity entity) {
        entities = new Entity[]{entity};
    }

    public Scene(Entity[] entities) {
        this.entities = entities;
    }

    public Entity[] GetEntities() {
        return entities;
    }

    public void AddEntity(Entity entity) {
        Entity[] newEntities = new Entity[entities.length + 1];
        System.arraycopy(entities, 0, newEntities, 0, entities.length);
        newEntities[entities.length] = entity;
        entities = newEntities;
    }

    public void RemoveEntity(Entity entity) {
        for(int i = 0; i < entities.length; i++) {
            if(entities[i] == entity) {
                entities[i] = null;
            }
        }
    }
}
