package main.java.coffeerun.Entities.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Utilities.Collider;
import main.java.coffeerun.Utilities.ScrollableObject;

/**
 * Espresso class extends Item
 */
@SuppressWarnings("ALL")
public class Espresso extends Item {

    private static boolean enabled = false;     // Boolean enabled default false.
    private static float time = 0.0f;           // Float time default 0.0f.

    /**
     * Espresso Constructor
     * <p>
     * This constructor takes in x and y positions and a bounds for out of bounds. It then creates a
     * new position, size, texture and hitbox based on the parameters.
     *
     * @param x - x position for espresso
     * @param y - y position for espresso
     * @param bounds - bounds for espresso
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Espresso(float x, float y, float bounds) {
        super(bounds);
        position = new Vector2(x, y);
        size = new Vector2(5, 5);
        texture = new Texture("espresso.png");
        hitBox = new Rectangle(position.x, position.y, size.x, size.y);
    }

    public Espresso(float x, float y, float bounds, boolean test) {
        super(bounds);
        position = new Vector2(x, y);
        size = new Vector2(5, 5);
        //texture = new Texture("espresso.png");
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
     * This method scrolls the espresso object, updates the hitbox position and checks if it needs
     * to update the espresso object as a whole if it gets enabled.
     *
     * @param deltaTime - time passed
     */
    @Override
    public void update(float deltaTime) {
        scroll();
        hitBox.setPosition(position.x, position.y);
    }

    /**
     * updateAllEspressos Method
     * <p>
     * This method checks if it is enabled, if so it starts counting time. If time is greater than
     * or equal to 1 second then it returns the scroll value back to the default and re-enables the
     * energy bar. It then sets the espresso object to disabled, and time back to 0.
     *
     * @param deltaTime - time passed
     */
    public static void updateAllEspressos(float deltaTime, EnergyBar e) {
        if (enabled) {
            time += deltaTime / 1000.0f;
            if (time >= 1.0f) {
                ScrollableObject.setScrollValue(-0.5f);
                e.enable();
                enabled = false;
                time = 0.0f;
            }
        }
    }

    /**
     * getCollider Method
     *
     * @return new Collider object with this instance
     */
    @Override
    public Collider getCollider() {
        return new Collider(this);
    }

    /**
     * enablePowerUp Method
     * <p>
     * This method enables the espresso object, increases the scroll value, and disables the energy
     * bar.
     */
    public void enablePowerUp(EnergyBar e) {
        enabled = true;
        ScrollableObject.setScrollValue(-1.0f);
        e.disable();
        e.makeFull();
    }
}
