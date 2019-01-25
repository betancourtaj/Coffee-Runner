package main.java.coffeerun.Utilities;

import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Entities.Entity;

/**
 * Collider class
 */
@SuppressWarnings("ALL")
public class Collider {

    private Entity thisEntity;      // Entity to check collision with.

    /**
     * Collider Construcor
     * <p>
     * This constructor takes in an entity and sets thisEntity to the one passed as a parameter.
     *
     * @param entity - entity colliding with
     */
    public Collider(Entity entity) {
        thisEntity = entity;
    }

    /**
     * collidesWith Method
     * <p>
     * This method uses AABB (Axis Aligned Bounding Box) Collision detection to determine if two squares
     * are colliding.
     *
     * @param other - other entity
     * @return true if the two squares are colliding; false otherwise.
     */
    public boolean collidesWith(Collider other) {
        Vector2 otherHalfSize = new Vector2(other.getSize().x / 2, other.getSize().y / 2);
        Vector2 otherPosition = new Vector2(other.getPosition().x + otherHalfSize.x, other.getPosition().y + otherHalfSize.y);

        Vector2 thisHalfSize = new Vector2(getSize().x / 2, getSize().y / 2);
        Vector2 thisPosition = new Vector2(getPosition().x + thisHalfSize.x, getPosition().y + thisHalfSize.y);

        float deltaX = otherPosition.x - thisPosition.x;
        float deltaY = otherPosition.y - thisPosition.y;

        float intersectX = Math.abs(deltaX) - (otherHalfSize.x + thisHalfSize.x);
        float intersectY = Math.abs(deltaY) - (otherHalfSize.y + thisHalfSize.y);

        return intersectX < 0.0f && intersectY < 0.0f;
    }

    /**
     * getSize Method
     *
     * @return thisEntity hitbox size
     */
    public Vector2 getSize() {
        return thisEntity.getHitboxSize();
    }

    /**
     * getPosition Method
     *
     * @return thisEntity hitbox position
     */
    public Vector2 getPosition() {
        return thisEntity.getHitboxPosition();
    }
}
