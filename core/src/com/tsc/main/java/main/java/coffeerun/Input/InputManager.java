package main.java.coffeerun.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import main.java.coffeerun.MainGame;

/**
 * InputManager class used for handling input calls.
 */
@SuppressWarnings("JavaDoc")
public class InputManager implements InputProcessor {

    public Vector3 touchPosition = new Vector3();    // Vector3 for touch input position.
    public boolean touched = false;                  // Boolean flag for touched.
    private MainGame mainGame;

    /**
     * InputManager Constructor
     * <p>
     * Sets input processor to this class.
     */
    public InputManager(MainGame game) {
        mainGame = game;
        Gdx.input.setInputProcessor(this);
    }

    /**
     * KeyDown method.
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * KeyUp method.
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * KeyTyped method.
     *
     * @param character
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * TouchDown method.
     * <p>
     * Gets touch position and sets flag value to true.
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return false.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPosition = mainGame.getCamera().unproject(new Vector3(screenX, screenY, 0.0f));
        System.out.println("X: " + screenX + " Y: " + screenY + " | " + "X: " + touchPosition.x + " Y: " + touchPosition.y);
        touched = true;
        return false;
    }

    /**
     * TouchUp method.
     * <p>
     * Gets touch position and sets flag value to false.
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return false.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchPosition = mainGame.getCamera().unproject(new Vector3(screenX, screenY, 0.0f));
        touched = false;
        return false;
    }

    /**
     * TouchDragged method.
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * MouseMoved method.
     *
     * @param screenX
     * @param screenY
     * @return
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Scrolled Method.
     *
     * @param amount
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
