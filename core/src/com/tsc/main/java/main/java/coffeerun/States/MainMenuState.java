package main.java.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import main.java.coffeerun.MainGame;
import main.java.coffeerun.Utilities.CRButton;

/**
 * Main Menu State for game.
 * <p>
 * Extends Game State.
 */
public class MainMenuState extends GameState {

    private CRButton playButton;        // CRButton for play button.
    private CRButton optionsButton;     // CRButton for options button.
    private CRButton scoresButton;      // CRButton for scores button.

    private Texture background;         // Texture for background image.
    private Texture title;

    private Color color;                // Color to clear background color.

    /**
     * MainMenuState Constructor
     * <p>
     * Instantiates all class objects.
     */
    public MainMenuState(MainGame game) {
        super(game);
        color = new Color(0, 0, 1, 1);

        playButton = new CRButton(-20.0f, -15.0f, 20.0f, 20.0f, new Texture("Start_button.png"), mainGame);
        optionsButton = new CRButton(-2.0f, -18.0f, 20.0f, 20.0f, new Texture("Option_button.png"), mainGame);
        scoresButton = new CRButton(35.0f, -18.0f, 5.0f, 5.0f, new Texture("trophy.png"), mainGame);

        background = new Texture("Menu_NoTitle.png");
        title = new Texture("title.png");
    }

    /**
     * <p>
     * MainMenuState Constructor
     * <p>
     *
     * @param color         - Color
     * @param playButton    - CRButton
     * @param optionsButton - CRButton
     * @param scoresButton  - CRButton
     * @param background    - Texture
     */
    public MainMenuState(Color color, CRButton playButton, CRButton optionsButton, CRButton scoresButton, Texture background, MainGame game) {
        super(game);
        this.color = color;

        this.playButton = playButton;
        playButton.setMiddleOrigin();
        this.optionsButton = optionsButton;
        this.scoresButton = scoresButton;

        this.background = background;
    }

    /**
     * Input method.
     * <p>
     * Checks if buttons have been pressed and changes game states
     * accordingly.
     */
    @Override
    public void input() {
        if (playButton.isPressed()) {
            mainGame.getStateManager().push(new PlayingState(mainGame));
        }
        if (optionsButton.isPressed()) {
            mainGame.getStateManager().push(new OptionsState(mainGame));
        }
    }

    /**
     * <p>
     * inputPlayButtonDebug method.
     * <p>
     *
     * @param playingState - PlayingState
     */
    public void inputPlayButtonDebug(PlayingState playingState) {
        if (playButton.isPressed()) {
            mainGame.getStateManager().push(playingState);
        }
    }

    /**
     * <p>
     * inputOptionsButtonDebug method.
     * <p>
     *
     * @param optionsState - OptionsState
     */
    public void inputOptionsButtonDebug(OptionsState optionsState) {
        if (optionsButton.isPressed()) {
            mainGame.getStateManager().push(optionsState);
        }
    }

    /**
     * <p>
     * inputScoresButtonDebug method.
     * <p>
     *
     * @param endMenu - EndMenuState
     */
    public void inputScoresButtonDebug(EndMenuState endMenu) {
        if (scoresButton.isPressed()) {
            mainGame.getStateManager().push(endMenu);
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_ALPHA_BITS);
        Gdx.gl.glEnable(GL20.GL_BLEND_DST_ALPHA);


        mainGame.getSpriteBatch().begin();
        mainGame.getSpriteBatch().draw(background, -mainGame.getViewPortWidth() / 2, -mainGame.getViewPortHeight() / 2, mainGame.getViewPortWidth(), mainGame.getViewPortHeight());
        mainGame.getSpriteBatch().draw(title, -30, 0, 60, 20);
        mainGame.getSpriteBatch().end();


        playButton.render();
        optionsButton.render();
        scoresButton.render();
    }

    /**
     * Dispose method.
     * <p>
     * Dispose all objects that are disposable.
     */
    @Override
    public void dispose() {
        playButton.dispose();
        optionsButton.dispose();
        scoresButton.dispose();
    }
}
