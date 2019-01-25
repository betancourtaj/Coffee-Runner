package main.java.coffeerun.IterationThreeTests;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.MainGame;
import main.java.coffeerun.States.MainMenuState;
import main.java.coffeerun.States.OptionsState;
import main.java.coffeerun.States.PausedState;
import main.java.coffeerun.States.PlayingState;
import main.java.coffeerun.Utilities.CRButton;

public class PauseStateUserStory extends GameTest {

    private Color color = Mockito.mock(Color.class);                    // Mock Color to clear background color.
    private CRButton mainMenuButton = Mockito.mock(CRButton.class);     // Mock CRButton for main menu button.
    private CRButton playButton = Mockito.mock(CRButton.class);         // Mock CRButton for play button.
    private CRButton optionsButton = Mockito.mock(CRButton.class);      // Mock CRButton for options button.
    private CRButton scoresButton = Mockito.mock(CRButton.class);       // Mock CRButton for scores button.
    private CRButton backToGameButton = Mockito.mock(CRButton.class);   // CRButton for back to game button.
    private Texture background = Mockito.mock(Texture.class);           // Texture for background image.

    private MainGame mainGame = Mockito.spy(MainGame.class);            // Mock MainGame for testing.
    private PausedState pauseMenu;                                      // Mock EndMenuState for testing.

    /**
     * Setup Method
     *
     * This method sets up the test environment by creating all the objects needed to run each
     * subsequent test.
     */
    @Before
    public void setUp() {
        super.init();
        mainGame.create(Mockito.spy(new MainMenuState(color, playButton, optionsButton, scoresButton, background, mainGame)));
        mainGame.getStateManager().push(Mockito.mock(PlayingState.class));
        pauseMenu = Mockito.spy(new PausedState(mainGame, color, mainMenuButton, backToGameButton, optionsButton));
        mainGame.getStateManager().push(new PausedState(mainGame, color, mainMenuButton, backToGameButton, optionsButton));
    }

    /**
     * MainMenuButtonTest
     *
     * This method checks if the main menu state is pushed to the top of the stack after pressing
     * the main menu button on the pause menu.
     */
    @Test
    public void mainMenuButtonTest() {
        Mockito.when(mainMenuButton.isPressed()).thenReturn(true);

        pauseMenu.input();
        Assert.assertEquals(Mockito.mock(MainMenuState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * BackToGameButtonTest Method
     *
     * This method checks if the back to game button pushes a new playing state to the top of the
     * stack.
     */
    @Test
    public void backToGameButtonTest(){
        Mockito.when(backToGameButton.isPressed()).thenReturn(true);
        pauseMenu.input();

        Assert.assertEquals(Mockito.mock(PlayingState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }

    /**
     * OptionsButtonTest Method
     *
     * This method tests if the options state is pushed to the top of the stack after the options
     * button is pressed in the paused state.
     */
    @Test
    public void optionsButtonTest(){
        Mockito.when(optionsButton.isPressed()).thenReturn(true);
        pauseMenu.inputOptions(Mockito.mock(OptionsState.class));

        Assert.assertEquals(Mockito.mock(OptionsState.class).getClass(), mainGame.getStateManager().states.peek().getClass());
    }
}
