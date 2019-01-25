package main.java.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import main.java.coffeerun.MainGame;
import main.java.coffeerun.Utilities.CRButton;

/**
 * OptionsState class.
 * <p>
 * Extends Game State
 */
public class OptionsState extends GameState {

    private CRButton backButton;    // CRButton for back button.
    private Texture background;
    /**
     * OptionsState Constructor.
     * <p>
     * Instantiates class objects.
     */
    public OptionsState(MainGame game) {
        super(game);
        backButton = new CRButton(0.0f, -12.0f, 20.0f, 20.0f, new Texture("Back_button.png"), mainGame);
        backButton.setMiddleOrigin();
        background = new Texture("endBackground.jpg");
    }

    /**
     * Input method.
     * <p>
     * Checks if buttons have been pressed and changes game states
     * accordingly.
     */
    @Override
    public void input() {
        if (backButton.isPressed()) {
            mainGame.getStateManager().pop();
        }
    }

    /**
     * updateDebug method.
     * <p>
     * Updates objects in "scene" over time.
     *
     * @param deltaTime - time passed
     */
    @Override
    public void update(float deltaTime) {

    }

    /**
     * Render method.
     * <p>
     * Render all objects in "scene."
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.getSpriteBatch().begin();
        mainGame.getSpriteBatch().draw(background, mainGame.getLeft(), mainGame.getBottom(), mainGame.getRight() * 2, mainGame.getTop() * 2);
        mainGame.getSpriteBatch().end();
        backButton.render();
    }

    /**
     * Dispose method.
     * <p>
     * Dispose all objects that are disposable.
     */
    @Override
    public void dispose() {
        backButton.dispose();
    }
}
