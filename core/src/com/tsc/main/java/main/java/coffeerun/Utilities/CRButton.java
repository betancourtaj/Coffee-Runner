package main.java.coffeerun.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import main.java.coffeerun.MainGame;

/**
 * CRButton Class
 * <p>
 * Extends Rectangle
 */
public class CRButton extends Rectangle {

    private ShapeRenderer shapeRenderer;    //ShapeRenderer to render Rectangle.
    private Texture texture;                //Texture to show texture over rectangle.
    private SpriteBatch spriteBatch;
    private MainGame mainGame;

    /**
     * CRButton constructor
     * <p>
     * Instantiate all class objects based on parameters entered.
     *
     * @param x - x position for button
     * @param y - y position for button
     * @param width - width for button
     * @param height - height for button
     * @param texture - texture for button
     */
    public CRButton(float x, float y, float width, float height, Texture texture, MainGame game) {
        super(x, y, width, height);

        super.width = width;
        super.height = height;
        /*
        x = positionX - (.5f * width);
        y = positionY + (.5f * height);*/
        super.x = x;
        super.y = y;

        shapeRenderer = new ShapeRenderer();
        this.texture = texture;
        mainGame = game;
        spriteBatch = mainGame.getSpriteBatch();
        spriteBatch.setProjectionMatrix(mainGame.getCamera().combined);
    }

    /**
     * CRButton Constructor.
     * <p>
     * Instantiate all class objects based on parameters entered.
     *
     * @param width - width of button
     * @param height - height of button
     * @param texture - texture of button
     */
    public CRButton(float width, float height, Texture texture, MainGame game) {
        this(0.0f, 0.0f, width, height, texture, game);
    }

    /**
     * IsPressed method.
     * <p>
     * Checks if button has been pressed.
     *
     * @return true if button was pressed; false otherwise.
     */
    public boolean isPressed() {
        if (mainGame.getInputManager().touched) {
            return ((mainGame.getInputManager().touchPosition.x <= super.x + width) && (mainGame.getInputManager().touchPosition.x > super.x)) && ((mainGame.getInputManager().touchPosition.y <= super.y + height) && (mainGame.getInputManager().touchPosition.y > super.y));
        }
        return false;
    }

    /**
     * Render method.
     * <p>
     * Render button rectangle and texture to screen.
     */
    public void render() {
        shapeRenderer.setProjectionMatrix(mainGame.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.end();

        spriteBatch.enableBlending();
        spriteBatch.begin();
        spriteBatch.draw(texture, super.x, super.y, super.width, super.height);
        spriteBatch.end();
    }

    /**
     * Dispose method.
     * <p>
     * Dispose texture and Sprite Batch objects.
     */
    public void dispose() {
        texture.dispose();
    }

    /**
     * SetMiddleOrigin method.
     * <p>
     * Sets the buttons origin position to the center
     * of the button as opposed to the bottom left-hand
     * corner.
     */
    public void setMiddleOrigin() {
        super.x -= super.width / 2;
        super.y -= super.height / 2;
    }
}
