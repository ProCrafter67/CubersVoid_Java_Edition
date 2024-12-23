package cv.objects.util;

public class Renderer {
    private Entity []entities;

    public Renderer() {
        entities = new Entity[]{};
    }

    public Renderer(Entity []nodes) {
        entities = nodes;
    }

    public void PushEntity(Entity entity) {
        entities[entities.length] = entity;
    }

    public void PopEntity() {
        entities[entities.length - 1] = null;
    }

    public void PopEntity(Entity entity) {
        for(int i = 0; i < entities.length; i++) {
            if(entities[i] == entity) {
                entities[i] = null;
            }
        }
    }

    public void Render() {
        for (Entity entity : entities) {
            if(entity != null)
                entity.Render();
        }
    }
}
