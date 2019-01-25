package main.java.coffeerun.IterationOneTests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.MainGame;
import main.java.coffeerun.States.EndMenuState;
import main.java.coffeerun.States.MainMenuState;
import main.java.coffeerun.States.OptionsState;
import main.java.coffeerun.States.PlayingState;
import main.java.coffeerun.Utilities.CRButton;

/**
 * MainMenuStateTest class used to test the MainMenuState button functionality.
 */
@SuppressWarnings("SpellCheckingInspection")
public class MainMenuStateTest extends GameTest {

    private CRButton playButton = Mockito.mock(CRButton.class);        // Mock CRButton for play button.
    private CRButton optionsButton = Mockito.mock(CRButton.class);     // Mock CRButton for options button.
    private CRButton scoresButton = Mockito.mock(CRButton.class);      // Mock CRButton for scores button.

    private Texture background = Mockito.mock(Texture.class);          // Mock Texture for background image.

    private Color color = Mockito.mock(Color.class);                       // Mock Color to clear background color.

    private MainGame mainGame = Mockito.spy(MainGame.class);                  // Mock MainGame class to create game stack.
    private MainMenuState mainMenu;                                        // Mock MainMenuState for testing.

    /*
      Each test involves mocking a button press, and then checking what state is on top of the current state stack- this indicates
      which state will be updated on the app screen. And we check if the button press led to the state by checking if the top of our state
      stack is equal to the mockito-class. So a Mockito's PlayingState.class would be pushed to the top of the state stack when we press the
      single player button.
     */

    /**
     * setUp Method.
     *
     * This is the setup function, this function gets called before any of the tests are run. This
     * method calls the GameTest class's init method which Mocks most of the required libgdx related
     * things to get the tests running. It then creates a new MainGame, and MainMenuState for
     * testing.
     */
    @Before
    public void setUp() {
        super.init();
        mainGame.create(Mockito.spy(new MainMenuState(color, playButton, optionsButton, scoresButton, background, mainGame)));
        mainMenu = Mockito.spy(new MainMenuState(color, playButton, optionsButton, scoresButton, background, mainGame));
    }

    /**
     * playButtonTest Method.
     *
     * This method mocks the play button in the MainMenuState class to always return true.
     * We then call the input test method that tests the play button.
     * Finally we assert that the button caused the playing state to be at the top of the stack.
     */
    @Test
    public void playButtonTest() {
        Mockito.when(playButton.isPressed()).thenReturn(true);

        mainMenu.inputPlayButtonDebug(Mockito.mock(PlayingState.class));
        Assert.assertEquals(Mockito.mock(PlayingState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * optionsButtonTest Method.
     *
     * This method mocks the play button in the MainMenuState class to always return true.
     * We then call the input test method that tests the options button.
     * Finally we assert that the button caused the options state to be at the top of the stack.
     */
    @Test
    public void optionsButtonTest() {
        Mockito.when(optionsButton.isPressed()).thenReturn(true);

        mainMenu.inputOptionsButtonDebug(Mockito.mock(OptionsState.class));
        Assert.assertEquals(Mockito.mock(OptionsState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * highscoresButtonTest Method.
     *
     * NOTICE: Currently there is no high score game state, so for the purposes of the End state,
     * we have the highscore button leading to the end state, this will be changed in the final
     * version.
     *
     * This method mocks the scores button in the MainMenuState class to always return true.
     * We then call the input test method that tests the scores button.
     * Finally we assert that the button caused the End menu state to be at the top of the stack.
     */
    @Test
    public void highscoreButtonTest() {
        Mockito.when(scoresButton.isPressed()).thenReturn(true);

        mainMenu.inputScoresButtonDebug(Mockito.mock(EndMenuState.class));
        Assert.assertEquals(Mockito.mock(EndMenuState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

}