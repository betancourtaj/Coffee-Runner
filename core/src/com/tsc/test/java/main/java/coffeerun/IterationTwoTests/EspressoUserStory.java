package main.java.coffeerun.IterationTwoTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.Entities.Items.Espresso;
import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Entities.Platform;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Level.Level;
import main.java.coffeerun.Utilities.ScrollableObject;

/**
 * EspressoUserStory Class extends GameTest
 */
public class EspressoUserStory extends GameTest {

    private Level level;                // Level object for simulated level.
    private Player player;              // Player object for simulated player.
    private EnergyBar energyBar;        // Energy bar for simulated Energy bar.
    private Espresso collideEspresso;   // Espresso object for simulated espresso item.

    /**
     * setUp Method.
     * <p>
     * This method sets up the environment for the following tests
     * <p>
     * Simulate player jump and generate espresso.
     */
    @Before
    public void setUp() {
        player = new Player(-45, 0.0f, Mockito.mock(Particle.class), true);
        energyBar = new EnergyBar(50);
        collideEspresso = new Espresso(-25, -30, 0.0f, true);
        level = Mockito.spy(new Level(player, energyBar));

        player.jump();
        level.generateEntity(collideEspresso);
    }

    /**
     * testInvincible Method
     * <p>
     * This method tests the first scenario in the Shot o' Espresso issue.
     * <p>
     * This method updates the level while the player is not on the ground.
     * <p>
     * After the player lands, check if the energy bar is disabled.
     */
    @Test
    public void testInvincible() {
        while (!player.isOnGround()) {
            level.updateDebug();
        }
        Assert.assertTrue(energyBar.isDisabled());
    }

    /**
     * testRestoreCaffeineBar Method
     * <p>
     * This method tests the second scenario in the Shot o' Espresso issue.
     * <p>
     * This method updates the level while the player is not on the ground.
     * <p>
     * After the player lands, check if the energy bar is full.
     */
    @Test
    public void testRestoreCaffeineBar() {
        while (!player.isOnGround()) {
            level.updateDebug();
        }
        Assert.assertEquals(100, energyBar.getEnergy());
    }

    /**
     * testDecreaseSpeed Method
     * <p>
     * This method tests the third scenario in the Shot o' Espresso issue.
     * <p>
     * This method updates the level while the player is not on the ground.
     * <p>
     * After the player lands, check the scroll speed is greater than default.
     */
    @Test
    public void testIncreasedSpeed() {
        while (!player.isOnGround()) {
            level.updateDebug();
        }
        Assert.assertEquals(-1.0f, ScrollableObject.getScrollValue(), 0.1f);
    }

}
