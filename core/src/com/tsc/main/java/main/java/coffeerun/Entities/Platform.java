package main.java.coffeerun.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.Collider;

/**
 * Platform class extends Entity
 */
@SuppressWarnings("SpellCheckingInspection")
public class Platform extends Entity {

    /**
     * Platform Constructor
     * <p>
     * This constructor takes in x and y positions and a bounds for out of bounds. It then creates
     * a new position, size, hitbox and texture depending on the parameters and random number for
     * texture.
     *
     * @param x - x position for platform
     * @param y - y position for platform
     * @param bounds - bounds for platform
     */
    public Platform(float x, float y, float bounds) {
        super(bounds);
        // int random number for platform texture.
        int RANDOM_NUMBER_FOR_TEXTURE_FILE = (int) (Math.random() * 6) + 1;
        texture = new Texture("platform" + RANDOM_NUMBER_FOR_TEXTURE_FILE + ".png");

        position = new Vector2(x, y);
        size = new Vector2(20, 5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y + (size.y / 8));
    }


    /**
     * Platform debug Constructor
     * <p>
     *
     * @param x - x position for platform
     * @param y - y position for platform
     * @param bounds - bounds for platform
     */
    public Platform(float x, float y, float bounds, boolean test) {
        super(bounds);
        //texture = new Texture("platform" + RANDOM_NUMBER_FOR_TEXTURE_FILE + ".png");

        position = new Vector2(x, y);
        size = new Vector2(20, 5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y + (size.y / 8));
    }


    /**
     * getTexture Method
     *
     * @return texture
     */
    @Override
    public Texture getTexture() {
        return texture;
    }

    /**
     * update Method
     * <p>
     * This method scrolls the platform and updates the hitbox position
     *
     * @param deltaTime - time passed
     */
    @Override
    public void update(float deltaTime) {
        scroll();
        hitBox.setPosition(position.x, position.y);
    }

    /**
     * getCollider Method
     *
     * @return new Collider object of this instance
     */
    @Override
    public Collider getCollider() {
        return new Collider(this);
    }
}
