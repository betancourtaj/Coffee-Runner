package main.java.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import main.java.coffeerun.MainGame;
import main.java.coffeerun.Utilities.CRButton;

/**
 * Paused state for game.
 */
public class PausedState extends GameState {

    private Color color;                    //Color to clear background Color.
    private CRButton mainMenuButton;    // CRButton for main menu button.
    private CRButton backToGameButton;  // CRButton for back to game button.
    private CRButton optionsButton;     // CRButton for options button.
    private Texture background;
    /**
     * PausedState Constructor
     * <p>
     * Instantiates all class objects.
     */
    public PausedState(MainGame game) {
        super(game);
        color = new Color(0.0f, 0.0f, 0.0f, 0.03f);

        mainMenuButton = new CRButton(-40.0f, -18.0f, 5.0f, 5.0f, new Texture("homeButton.png"), mainGame);
        backToGameButton = new CRButton(35.0f, -18.0f, 5.0f, 5.0f, new Texture("Back_button.png"), mainGame);
        optionsButton = new CRButton(20, 20, new Texture("Option_button.png"), mainGame);
        optionsButton.setMiddleOrigin();
        background = new Texture("endBackground.jpg");
    }

    public PausedState(MainGame game, Color color, CRButton mainMenuButton, CRButton backToGameButton, CRButton optionsButton) {
        super(game);
        this.color = color;

        this.mainMenuButton = mainMenuButton;
        this.backToGameButton = backToGameButton;
        this.optionsButton = optionsButton;
        optionsButton.setMiddleOrigin();
    }

    /**
     * Input method.
     * <p>
     * Checks if buttons have been pressed and changes game states
     * accordingly.
     */
    @Override
    public void input() {
        if (mainMenuButton.isPressed()) {
            mainGame.getStateManager().returnToMainMenu();
        }
        if (backToGameButton.isPressed()) {
            mainGame.getStateManager().pop();
        }
        if (optionsButton.isPressed()) {
            mainGame.getStateManager().push(new OptionsState(mainGame));
        }
    }

    public void inputOptions(GameState state) {
        if(optionsButton.isPressed()) {
            mainGame.getStateManager().push(state);
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
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.getSpriteBatch().begin();
        mainGame.getSpriteBatch().draw(background, mainGame.getLeft(), mainGame.getBottom(), mainGame.getRight() * 2, mainGame.getTop() * 2);
        mainGame.getSpriteBatch().end();
        mainMenuButton.render();
        backToGameButton.render();
        optionsButton.render();

    }

    /**
     * Dispose method.
     * <p>
     * Dispose all objects that are disposable.
     */
    @Override
    public void dispose() {
        mainMenuButton.dispose();
        backToGameButton.dispose();
        optionsButton.dispose();
    }
}
