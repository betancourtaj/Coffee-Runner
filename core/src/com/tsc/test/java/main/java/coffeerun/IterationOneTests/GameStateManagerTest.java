package main.java.coffeerun.IterationOneTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.coffeerun.States.GameStateManager;
import main.java.coffeerun.States.MainMenuState;
import main.java.coffeerun.States.OptionsState;
import main.java.coffeerun.States.PausedState;
import main.java.coffeerun.States.PlayingState;

/**
 * GameStateManagerTest class used to test the GameStateManager class.
 */
public class GameStateManagerTest {

    private GameStateManager gameStateManager = new GameStateManager();         // GameStateManager to test game states functionality.
    private PlayingState playingState = Mockito.mock(PlayingState.class);       // Mock playing state for testing.
    private PausedState pausedState = Mockito.mock(PausedState.class);          // Mock paused state for testing.
    private OptionsState optionsState = Mockito.mock(OptionsState.class);       // Mock options state for testing.
    private MainMenuState mainMenuState = Mockito.mock(MainMenuState.class);    // Mock main menu state.

    /**
     * setUp Method.
     *
     * This is the setup function, this function gets called before any of the tests are run.
     * This method pushes a new Mock main menu state to the top of the stack.
     */
    @Before
    public void setUp() {
        gameStateManager.push(mainMenuState);
    }

    /**
     * pushTest1 Method.
     *
     * This method asserts if the main menu state is actually at the top of the stack.
     */
    @Test
    public void pushTest1() {
        Assert.assertEquals(mainMenuState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * pushTest2 Method.
     *
     * This method asserts whether the paused state is at the top of the stack after pushing
     * an instance of the playing state and paused state.
     */
    @Test
    public void pushTest2() {
        gameStateManager.push(playingState);
        gameStateManager.push(pausedState);
        Assert.assertEquals(pausedState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * popTest1 Method.
     *
     * This method asserts if the state stack size is zero after popping the main menu state.
     */
    @Test
    public void popTest1() {
        gameStateManager.pop();
        Assert.assertEquals(0, gameStateManager.states.size());
    }

    /**
     * popTest2 Method.
     *
     * This method asserts if the paused state is the top of the stack after pushing three instances
     * of playing state, paused state, and options state, then popping the top state.
     */
    @Test
    public void popTest2() {
        gameStateManager.push(playingState);
        gameStateManager.push(pausedState);
        gameStateManager.push(optionsState);
        gameStateManager.pop();
        Assert.assertEquals(pausedState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * setTest1 Method.
     *
     * This method asserts if the playing state is still at the top of the stack after calling
     * the set method with an instance of playing state as a parameter.
     */
    @Test
    public void setTest1() {
        gameStateManager.set(playingState);
        Assert.assertEquals(playingState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * setTest2 Method.
     *
     * This method asserts if the options state is at the top of the stack after pushing and setting
     * one instances of playing state, and setting an instance of paused state, and options state.
     */
    @Test
    public void setTest2() {
        gameStateManager.push(playingState);
        gameStateManager.set(pausedState);
        gameStateManager.set(optionsState);
        Assert.assertEquals(optionsState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * disposeTest1 Method.
     *
     * This method asserts if the size of the stack is zero after disposing the top state.
     */
    @Test
    public void disposeTest1() {
        gameStateManager.dispose();
        Assert.assertEquals(0, gameStateManager.states.size());
    }

    /**
     * returnToMainMenuTest1 Method.
     *
     * This method asserts if main menu state is the top of the stack after having many playing state
     * instances pushed to the top of the stack.
     */
    @Test
    public void returnToMainMenuTest1() {
        gameStateManager.push(playingState);
        gameStateManager.push(playingState);
        gameStateManager.push(playingState);
        gameStateManager.push(playingState);
        gameStateManager.push(playingState);
        gameStateManager.returnToMainMenu();
        Assert.assertEquals(mainMenuState.getClass(), gameStateManager.states.peek().getClass());
    }

    /**
     * returnToMainMenuTest2 Method.
     *
     * This method asserts if the size of the stack is 0 after popping the main menu state and then
     * calling the returnToMainMenu method.
     */
    @Test
    public void returnToMainMenuTest2() {
        gameStateManager.pop();
        gameStateManager.returnToMainMenu();
        Assert.assertEquals(0, gameStateManager.states.size());
    }
}