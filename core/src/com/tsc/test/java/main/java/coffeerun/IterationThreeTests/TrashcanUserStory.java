package main.java.coffeerun.IterationThreeTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Entities.Platform;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Entities.TrashCan;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Level.Level;
import main.java.coffeerun.Utilities.ScrollableObject;

public class TrashcanUserStory extends GameTest {

    private Level level;                // Level object for simulated level.
    private Player player;              // Player object for simulated player.
    private EnergyBar energyBar;        // Energy bar for simulated Energy bar.
    private TrashCan collideTrashcan;   // Trashcan for collision.

    /**
     * Setup Method
     *
     * This method sets up the test environment by creating all the objects needed to run each
     * subsequent test.
     */
    @Before
    public void setUp() {
        player = new Player(-45, 0.0f, Mockito.mock(Particle.class), true);
        energyBar = new EnergyBar(50);
        collideTrashcan = new TrashCan(-20, -30, 0.0f, true);
        level = Mockito.spy(new Level(player, energyBar));

        player.jump();
        level.generateEntity(collideTrashcan);
    }

    /**
     * TestStop Method
     *
     * This method tests if the player gets stopped when they collide with a trashcan object.
     */
    @Test
    public void testStop() {
        boolean stopped = false;
        while(!player.isOnGround()) {
            level.updateDebug();
            if(ScrollableObject.getScrollValue() == 0.0f) {
                stopped = true;
            }
        }

        Assert.assertTrue(stopped);
    }

    /**
     * TestDepleteCaffeineBar Method
     *
     * This method tests if the energy bar keeps dropping even though the player is stopped after
     * colliding with a trashcan.
     */
    @Test
    public void testDepleteCaffeineBar() {
        int startEnergy = energyBar.getEnergy();
        while (!player.isOnGround()) {
            level.updateDebug();
        }
        Assert.assertTrue(startEnergy > energyBar.getEnergy());
    }

    /**
     * JumpOverTrashcan Method
     *
     * This method tests if the player is able to jump over a trashcan by having the player
     * jump and having a trashcan move beneath them.
     */
    @Test
    public void jumpOverTrashcan() {
        boolean hitTrashCan = false;
        level.removeEntity(collideTrashcan);
        collideTrashcan = new TrashCan(-23, -45, 0.0f, true);
        while(!player.isOnGround()) {
            level.updateDebug();
            collideTrashcan.scroll();
            if(player.isTripped()) {
                hitTrashCan = true;
            }
        }

        Assert.assertFalse(hitTrashCan);
    }
}
