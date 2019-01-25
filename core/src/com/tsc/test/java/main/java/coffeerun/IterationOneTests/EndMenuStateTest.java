package main.java.coffeerun.IterationOneTests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.MainGame;
import main.java.coffeerun.States.EndMenuState;
import main.java.coffeerun.States.MainMenuState;
import main.java.coffeerun.States.PlayingState;
import main.java.coffeerun.Utilities.CRButton;

public class EndMenuStateTest extends GameTest {
    private Color color = Mockito.mock(Color.class);                        // Mock Color to clear background color.
    private CRButton mainMenuButton = Mockito.mock(CRButton.class);     // Mock CRButton for main menu button.
    private Texture winner = Mockito.mock(Texture.class);               // Mock Texture for winner texture.
    private CRButton replayButton = Mockito.mock(CRButton.class);       // Mock CRButton for replay button.
    private CRButton nextLevelButton = Mockito.mock(CRButton.class);    // Mock CRButton for nextLevelButton.

    private CRButton playButton = Mockito.mock(CRButton.class);         // Mock CRButton for play button.
    private CRButton optionsButton = Mockito.mock(CRButton.class);      // Mock CRButton for options button.
    private CRButton scoresButton = Mockito.mock(CRButton.class);       // Mock CRButton for scores button.

    private Texture background = Mockito.mock(Texture.class);           // Texture for background image.

    private MainGame mainGame = Mockito.spy(MainGame.class);                   // Mock MainGame for testing.
    private EndMenuState endMenu;                                          // Mock EndMenuState for testing.

    /**
     * Each test involves mocking a button press, and then checking what state is on top of the current state stack- this indicates
     * which state will be updated on the app screen. And we check if the button press led to the state by checking if the top of our state
     * stack is equal to the mockito-class. So a Mockito's PlayingState.class would be pushed to the top of the state stack when we press the
     * single player button.
     */

    /**
     * setUp Method.
     *
     * This is the setup function, this function gets called before any of the tests are run. This
     * method calls the GameTest class's init method which Mocks most of the required libgdx related
     * things to get the tests running. It then creates a new MainGame, pushes a PlayingState to the
     * stack, and EndMenuState for testing.
     */
    @Before
    public void setUp() {
        super.init();
        mainGame.create(Mockito.spy(new MainMenuState(color, playButton, optionsButton, scoresButton, background, mainGame)));
        mainGame.getStateManager().push(Mockito.mock(PlayingState.class));
        endMenu = Mockito.spy(new EndMenuState(color, mainMenuButton, winner, replayButton, nextLevelButton, mainGame));
    }

    /**
     * mainMenuButtonTest Method.
     *
     * This method mocks the main menu button in the EndMenuState class to always return true.
     * We then call the input test method that tests the main menu button.
     * Finally we assert that the button caused the Main Menu state to be at the top of the stack.
     */
    @Test
    public void mainMenuButtonTest() {
        Mockito.when(mainMenuButton.isPressed()).thenReturn(true);

        endMenu.inputMainMenuButtonDebug();
        Assert.assertEquals(Mockito.mock(MainMenuState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * replayButtonTest Method.
     *
     * NOTICE: The replay and next buttons have the same functionality, this is because later in
     * another iteration we will add level ID's to the playing state, allowing us to select which
     * level to return the player to after pressing either button.
     *
     * This method mocks the replay button in the EndMenuState class to always return true.
     * We then call the input test method that tests the replay button.
     * Finally we assert that the button caused the playing state state to be at the top of the stack.
     */
    @Test
    public void replayButtonTest() {
        Mockito.when(replayButton.isPressed()).thenReturn(true);

        endMenu.inputReplayButtonDebug(Mockito.mock(PlayingState.class));
        Assert.assertEquals(Mockito.mock(PlayingState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * nextLevelButtonTest Method.
     *
     * NOTICE: The replay and next buttons have the same functionality, this is because later in
     * another iteration we will add level ID's to the playing state, allowing us to select which
     * level to return the player to after pressing either button.
     *
     * This method mocks the next level button in the EndMenuState class to always return true.
     * We then call the input test method that tests the next level button.
     * Finally we assert that the button caused the playing state state to be at the top of the stack.
     */
    @Test
    public void nextLevelButtonTest() {
        Mockito.when(nextLevelButton.isPressed()).thenReturn(true);

        endMenu.inputNextButtonDebug(Mockito.mock(PlayingState.class));
        Assert.assertEquals(Mockito.mock(PlayingState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

}