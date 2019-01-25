package main.java.coffeerun.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.Collider;
import main.java.coffeerun.Interfaces.CollidableInterface;
import main.java.coffeerun.Utilities.ScrollableObject;
import main.java.coffeerun.Interfaces.EntityInterface;

/**
 * Abstract class Entity extends ScrollableObject and implements EntityInterface and CollidableInterface
 */
@SuppressWarnings("ALL")
public abstract class Entity extends ScrollableObject implements EntityInterface, CollidableInterface {

    protected Vector2 position;     // Vector2 for position.
    protected Vector2 size;         // Vector2 for size.
    protected Texture texture;      // Texture for object.
    protected Rectangle hitBox;     // Rectangle for hitbox.
    protected float rotation = 0;
    protected float bounds;         // Float bounds for out of bounds.

    /**
     * Entity Constructor\
     * <p>
     * This constructor sets the bounds to out of bound parameter.
     *
     * @param bounds - bounds for entity to despawn
     */
    public Entity(float bounds) {
        this.bounds = bounds;
    }

    /**
     * scroll Method
     * <p>
     * This method increases the x position of the Entity by the xScroll value.
     */
    public void scroll() {
        position.x += xScroll;
    }

    /**
     * Abstract getTexture Method
     *
     * @return texture
     */
    public abstract Texture getTexture();

    /**
     * Abstract update Method
     *
     * @param deltaTime - time passed
     */
    public abstract void update(float deltaTime);

    /**
     * getPosition Method
     *
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * getSize Method
     *
     * @return size
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * getHitboxSize Method
     *
     * @return new Vector2 of rectangle size
     */
    public Vector2 getHitboxSize() {
        Vector2 v = new Vector2();
        hitBox.getSize(v);
        return v;
    }

    /**
     * getHitboxPosition Method
     *
     * @return new Vector2 of rectangle position
     */
    public Vector2 getHitboxPosition() {
        Vector2 v = new Vector2();
        hitBox.getPosition(v);
        return v;
    }

    /**
     * isOutOfBounds Method
     *
     * @return true if position x is less than bounds minus size; false otherwise
     */
    public boolean isOutOfBounds() {
        return position.x <= bounds - size.x;
    }

    /**
     * Abstract getCollider Method
     *
     * @return new Collider object
     */
    public abstract Collider getCollider();

    public float getRotation() {
        return rotation;
    }
}
