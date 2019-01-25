package main.java.coffeerun.Entities.Particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.ScrollableObject;

public class Particle extends ScrollableObject {

    private Vector2 position;
    private Vector2 size;
    private Texture texture;
    private boolean enabled = false;
    private float time = 0.0f;
    private float origin;

    public Particle(Vector2 position, Vector2 size, Texture texture) {
        this.position = position;
        origin = position.x;
        this.size = size;
        this.texture = texture;
    }

    public void update(float deltaTime) {
        if(enabled) {
            position.x += xScroll;
            time += deltaTime / 1000.0f;
            if (time <= 0.05f) {
                position.y += 0.1;
            } else {
                enabled = false;
                time = 0.0f;
                position.x = origin;
            }
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void enable(float yPos) {
        enabled = true;
        position.y = yPos;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }
}
