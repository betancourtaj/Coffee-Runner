package main.java.coffeerun.States;

import main.java.coffeerun.MainGame;

/**
 * Super class for all Game States.
 */
public abstract class GameState {

    protected MainGame mainGame;

    public GameState(MainGame game) {
        mainGame = game;
    }

    // Abstract methods used in each Game State class.
    public abstract void input();

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void dispose();
}
