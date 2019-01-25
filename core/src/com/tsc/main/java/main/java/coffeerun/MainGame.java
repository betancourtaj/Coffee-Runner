package main.java.coffeerun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.java.coffeerun.Input.InputManager;
import main.java.coffeerun.States.GameStateManager;
import main.java.coffeerun.States.MainMenuState;

import java.util.Random;

/**
 * Main class of our game.
 */
public class MainGame extends ApplicationAdapter {

    private InputManager input;       // Input manager for handling input
    private GameStateManager gameStateManager;  // Stack of game states.
    private Camera camera;            // Camera for view.
    private SpriteBatch spriteBatch;    // SpriteBatch for game.
    private Random random;
    private float viewPortHeight;     // Variable for viewport Height;
    private float viewPortWidth;      // Variable for viewport Width;
    private float pixelHeight;        // Variable for pixel Height;
    private float pixelWidth;         // Variable for pixel Width;

    /**
     * Create method to instantiate objects and set variables.
     */
    @Override
    public void create() {

        /*
          Set random for entire project.
         */
        random = new Random();

        /*
          Set pixel heights before setting the viewport.
         */
        pixelWidth = Gdx.graphics.getWidth();
        pixelHeight = Gdx.graphics.getHeight();

        /*
          Create Orthographic camera and set the viewport size variables.
         */
        camera = new OrthographicCamera(85, 40);
        viewPortWidth = camera.viewportWidth;
        viewPortHeight = camera.viewportHeight;

        /*
          Instantiate input handler object and states stack.
          Instantiate spritebatch object.
          Also push new MainMenuState object to the top of the stack
          to begin the game.
         */
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        input = new InputManager(this);
        gameStateManager = new GameStateManager();
        gameStateManager.push(new MainMenuState(this));
    }

    public void create(MainMenuState mainMenuState) {
        /*
          Set pixel heights before setting the viewport.
         */
        pixelWidth = Gdx.graphics.getWidth();
        pixelHeight = Gdx.graphics.getHeight();

        /*
          Create Orthographic camera and set the viewport size variables.
         */
        camera = new OrthographicCamera(85, 40);
        viewPortWidth = camera.viewportWidth;
        viewPortHeight = camera.viewportHeight;

        /*
          Instantiate input handler object and states stack.
          Also push new MainMenuState object to the top of the stack
          to begin the game.
         */
        input = new InputManager(this);
        gameStateManager = new GameStateManager();
        gameStateManager.push(mainMenuState);
    }

    /**
     * Render method.
     * <p>
     * Goes through the input, update, and render methods of each game State
     * on the top of the stack.
     */
    @Override
    public void render() {
        camera.update();

        gameStateManager.render();
    }

    /**
     * Dispose method.
     * <p>
     * Go through each state in the stack, and pop them.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        gameStateManager.dispose();
    }

    public InputManager getInputManager() {
        return input;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public GameStateManager getStateManager() {
        return gameStateManager;
    }

    public Camera getCamera() {
        return camera;
    }

    public float getViewPortHeight() {
        return viewPortHeight;
    }

    public float getViewPortWidth() {
        return viewPortWidth;
    }

    public float getBottom() {
        return -viewPortHeight / 2;
    }

    public float getTop() {
        return viewPortHeight / 2;
    }

    public float getLeft() {
        return -viewPortWidth / 2;
    }

    public float getRight() {
        return viewPortWidth / 2;
    }

    public float getPixelWidth() {
        return pixelWidth;
    }

    public Random getRandom() {
        return random;
    }

}
