package main.java.coffeerun.States;

import java.util.Stack;

/**
 * GameStateManager class
 */
public class GameStateManager {

    public Stack<GameState> states;     // Stack to hold all GameStates.
    private long last_time = System.nanoTime();
    private int framesPerSecond;
    private float frameTime;


    /**
     * GameStateManager Constructor
     * <p>
     * This constructor instantiates the states stack object.
     */
    public GameStateManager() {
        states = new Stack<GameState>();
    }

    /**
     * push Method.
     * <p>
     * This method takes in a GameState and pushes it to the top of the stack.
     *
     * @param state - GameState
     */
    public void push(GameState state) {
        states.push(state);
    }

    /**
     * pop Method.
     * <p>
     * This method peeks the top of the stack, disposes of objects in the state, then pops it from
     * the stack.
     */
    public void pop() {
        states.peek().dispose();
        states.pop();
    }

    /**
     * set Method.
     * <p>
     * This method takes a GameState object and pops the top of the stack, then pushes the state passed
     * to the to of the stack.
     *
     * @param state - GameState
     */
    public void set(GameState state) {
        pop();
        push(state);
    }

    /**
     * render Method.
     * <p>
     * This method runs the input, update and render methods of the top state on the stack.
     */
    public void render() {

        long time = System.nanoTime();
        int deltaTime = (int) ((time - last_time) / 1000000);
        last_time = time;
        frameTime += deltaTime / 1000.0f;
        framesPerSecond++;
        if(frameTime >= 1.0) {
            System.out.println("FramesPerSecond: " + framesPerSecond);
            framesPerSecond = 0;
            frameTime = 0.0f;
        }

        states.peek().input();
        states.peek().update(deltaTime);
        states.peek().render();
    }

    /**
     * dispose Method.
     * <p>
     * This method will pop each state of the stack until the stack is empty.
     */
    public void dispose() {
        while (!states.isEmpty()) {
            pop();
        }
    }

    /**
     * ReturnToMainMenu method.
     * <p>
     * Pop all states down to the main menu state (1st state);
     */
    public void returnToMainMenu() {
        while (states.size() > 1) {
            states.pop();
        }
    }
}
