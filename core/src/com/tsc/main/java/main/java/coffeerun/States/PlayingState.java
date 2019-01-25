package main.java.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import main.java.coffeerun.Level.Level;
import main.java.coffeerun.MainGame;
import main.java.coffeerun.Utilities.CRButton;

/**
 * Playing State for game.
 */
public class PlayingState extends GameState {

    private Color color;             // Color to clear background color.
    private CRButton pauseButton;   // CRButton for pause button.
    private Level level;

    /**
     * PlayingState Constructor
     * <p>
     * Instantiates all class objects.
     */
    public PlayingState(MainGame game) {
        super(game);
        color = new Color(0, 0, 0, 1);
        pauseButton = new CRButton(35.0f, 15.0f, 5.0f, 5.0f, new Texture("Settings_button.png"), mainGame);
        level = new Level(game);
    }

    /**
     * Input method.
     * <p>
     * Checks if buttons have been pressed and changes game states
     * accordingly.
     */
    @Override
    public void input() {
        if (pauseButton.isPressed()) {
            mainGame.getStateManager().push(new PausedState(mainGame));
        }
        level.input();
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
        level.update(deltaTime);
    }

    /**
     * Render method.
     * <p>
     * Render all objects in "scene."
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.render();
        pauseButton.render();
    }

    /**
     * Dispose method.
     * <p>
     * Dispose all objects that are disposable.
     */
    @Override
    public void dispose() {
        pauseButton.dispose();
    }
}
