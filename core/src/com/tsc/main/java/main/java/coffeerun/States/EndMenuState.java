package main.java.coffeerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import main.java.coffeerun.MainGame;
import main.java.coffeerun.Utilities.CRButton;

/**
 * EndMenuState class
 */
public class EndMenuState extends GameState {

    private Color color;                    // Color for clearing background color.
    private CRButton mainMenuButton;    // CRButton for main menu button.
    private Texture winnerTexture;      // Texture for winner image.
    private CRButton replayButton;      // CRButton for replay button.
    private CRButton nextLevelButton;   // CRButton for next level button.
    private Texture background;

    /**
     * EndMenuState Constructor
     * <p>
     * This constructor instantiates all class variables for use within the class.
     */
    public EndMenuState(MainGame game) {
        super(game);

        color = new Color(0.0f, 0.0f, 0.0f, 0.03f);
        nextLevelButton = new CRButton(35.0f, -20.0f, 5.0f, 5.0f, new Texture("nextButton.png"), mainGame);
        replayButton = new CRButton(-40.0f, -20.0f, 7.0f, 7.0f, new Texture("Back_button.png"), mainGame);
        mainMenuButton = new CRButton(-5.0f, -20.0f, 10.0f, 10.f, new Texture("homeButton.png"), mainGame);
        winnerTexture = new Texture("winner.png");
        background = new Texture("endBackground.jpg");
    }

    /*** <p>
     * EndMenuState Constructor
     * <p>
     *
     * @param color           - Color
     * @param mainMenuButton  - CRButton
     * @param winnerTexture   - Texture
     * @param replayButton    - CRButton
     * @param nextLevelButton - CRButton
     */
    public EndMenuState(Color color, CRButton mainMenuButton, Texture winnerTexture, CRButton replayButton, CRButton nextLevelButton, MainGame game) {
        super(game);
        this.color = color;
        this.nextLevelButton = nextLevelButton;
        this.replayButton = replayButton;
        this.mainMenuButton = mainMenuButton;
        this.winnerTexture = winnerTexture;
    }

    /**
     * input Method.
     * <p>
     * This method tests if the main menu button or replay button are pressed.
     * If any of those buttons are pressed, it will either return the game to the main menu, push a
     * new playing state, or push the previous playing state.
     */
    @Override
    public void input() {
        if (mainMenuButton.isPressed()) {
            mainGame.getStateManager().returnToMainMenu();
        }
        if (replayButton.isPressed()) {
            mainGame.getStateManager().returnToMainMenu();
            mainGame.getStateManager().push(new PlayingState(mainGame));
        }
        if (nextLevelButton.isPressed()) {
            mainGame.getStateManager().returnToMainMenu();
            mainGame.getStateManager().push(new PlayingState(mainGame));
        }

    }

    /**
     * <p>
     * inputReplayButtonDebug method.
     * <p>
     *
     * @param playingState - Playing State
     */
    public void inputReplayButtonDebug(PlayingState playingState) {
        if (replayButton.isPressed()) {
            mainGame.getStateManager().pop();
            mainGame.getStateManager().pop();
            mainGame.getStateManager().push(playingState);
        }
    }

    /**
     * <p>
     * inputNextButtonDebug method.
     * <p>
     *
     * @param playingState - Playing State
     */
    public void inputNextButtonDebug(PlayingState playingState) {
        if (nextLevelButton.isPressed()) {
            mainGame.getStateManager().pop();
            mainGame.getStateManager().pop();
            mainGame.getStateManager().push(playingState);
        }
    }

    /**
     * <p>
     * inputMainMenuButtonDebug method.
     * <p>
     */
    public void inputMainMenuButtonDebug() {
        if (mainMenuButton.isPressed()) {
            mainGame.getStateManager().pop();
        }
    }

    /**
     * update Method.
     * <p>
     * This method will update all the entities in this state that move or need updating over time.
     *
     * @param deltaTime - time passed
     */
    @Override
    public void update(float deltaTime) {

    }

    /**
     * render Method.
     * <p>
     * This method will render all entities in this state.
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainGame.getSpriteBatch().begin();
        mainGame.getSpriteBatch().draw(background, mainGame.getLeft(), mainGame.getBottom(), mainGame.getRight() * 2, mainGame.getTop() * 2);
        mainGame.getSpriteBatch().draw(winnerTexture, 0.0f - (40.0f / 2.0f), 0.0f - (20.0f / 2.0f), 40.0f, 20.0f);
        mainGame.getSpriteBatch().end();

        mainMenuButton.render();
        replayButton.render();
        nextLevelButton.render();

    }

    /**
     * dispose Method.
     * <p>
     * This method properly disposes of all objects in this state.
     */
    @Override
    public void dispose() {
        mainMenuButton.dispose();
        replayButton.dispose();
        nextLevelButton.render();
        winnerTexture.dispose();
    }
}
