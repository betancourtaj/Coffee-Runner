package main.java.coffeerun.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * BackgroundBufferImage class extends ScrollableObject
 */
public class BackgroundBufferImage extends ScrollableObject {

    private Texture texture;    // Texture for texture.
    private Vector2 position;   // Vector2 for position.
    private Vector2 size;       // Vector2 for size.
    private float bounds;       // Float for bounds.

    /**
     * BackgroundBufferImage Constructor
     * <p>
     * This constructor takes in x and y positions, with and height values, and a bounds for out of
     * bounds. It then creates a new texture, position, and size based on parameters.
     *
     * @param x - x position of BackgroundBufferImage
     * @param y - y position of BackgroundBufferImage
     * @param width - width of BBI
     * @param height - height of BBI
     * @param bounds - bounds for BBI
     */
    public BackgroundBufferImage(float x, float y, float width, float height, float bounds) {
        texture = new Texture("backgroundPlaying.png");
        position = new Vector2(x, y);
        size = new Vector2(width, height);
        this.bounds = bounds;
    }

    /**
     * update Method
     * <p>
     * This method adds the xScroll value to the position.x value.
     */
    public void update() {
        position.x += xScroll;
    }

    /**
     * getTexture Method
     *
     * @return texture
     */
    public Texture getTexture() {
        return texture;
    }

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
     * isOutOfBounds Method
     *
     * @return true if position.x is less than or equal to bounds minus size; false otherwise.
     */
    public boolean isOutOfBounds() {
        return position.x <= bounds - size.x;
    }
}
