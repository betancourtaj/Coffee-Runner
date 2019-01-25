package main.java.coffeerun.Entities.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.Collider;

/**
 * Coffee class extends Item
 */
public class Coffee extends Item {

    /**
     * Coffee Constructor
     * <p>
     * This constructor takes in an x and y position and a bounds for out of bounds. It then creates
     * a new position, texture, size and hitbox according to the parameters.
     *
     * @param x - x position for coffee
     * @param y - y position for coffee
     * @param bounds - bounds for coffee
     */
    public Coffee(float x, float y, float bounds) {
        super(bounds);
        texture = new Texture("coffee.png");
        position = new Vector2(x, y);
        size = new Vector2(5, 5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y);
    }

    /**
     * Coffee Constructor
     * <p>
     * <p>
     * Test
     */
    public Coffee(float x, float y, float bounds, boolean test) {
        super(bounds);
        //texture = new Texture("coffee.png");
        position = new Vector2(x, y);
        size = new Vector2(5, 5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y);
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
     * This method scrolls the Coffee Item and updates the hitbox position.
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
     * @return a new collider object with this instance.
     */
    @Override
    public Collider getCollider() {
        return new Collider(this);
    }
}
