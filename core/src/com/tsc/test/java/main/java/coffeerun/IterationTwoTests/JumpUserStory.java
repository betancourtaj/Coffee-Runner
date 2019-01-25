package main.java.coffeerun.IterationTwoTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Entities.Platform;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Level.Level;

/**
 * JumpUserStory Class extends GameTest
 */
public class JumpUserStory extends GameTest {

    private Level level;                // Level object for simulated level.
    private Player player;              // Player object for simulated player.
    private Platform platform;          // Platform object for simulated platform.
    private EnergyBar energyBar;
    boolean platformSpawned;    // Boolean flag for platform spawned.

    /**
     * setUp Method
     * <p>
     * This method sets up the environment for the following tests
     * <p>
     * Simulates player jump.
     */
    @Before
    public void setUp() {
        energyBar = new EnergyBar(100);
        player = new Player(-45, 0.0f, Mockito.mock(Particle.class), true);
        platform = new Platform(-30, -45, 0.0f, true);
        level = Mockito.spy(new Level(player, energyBar));
        platformSpawned = false;

        player.jump();
    }

    /**
     * jumpFromGroundLandOnPlatformTest Method
     * <p>
     * This method tests the first scenario in the Jumping issue.
     * <p>
     * This method updates the level while the player is not on a platform. If the player's velocity
     * becomes negative and a platform has not spawned, spawn a platform.
     * <p>
     * Check if player is on a platform.
     */
    @Test
    public void jumpFromGroundLandOnPlatformTest() {
        while (!player.isOnPlatform()) {
            level.updateDebug();
            if (player.getVelocity() <= 0 && !platformSpawned) {
                level.generateEntity(platform);
                platformSpawned = true;
            }
        }

        Assert.assertTrue(player.isOnPlatform());
    }

    /**
     * jumpFromGroundOntoGround Method
     * <p>
     * This method tests the second scenario in the Jumping issue.
     * <p>
     * This method updates the level while the player is not on the ground.
     * <p>
     * Check if player is on the ground.
     */
    @Test
    public void jumpFromGroundOntoGround() {
        while (!player.isOnGround()) {
            level.updateDebug();
        }

        Assert.assertTrue(player.isOnGround());
    }

    /**
     * jumpFromGroundOntoGround Method
     * <p>
     * This method tests the third scenario in the Jumping issue.
     * <p>
     * This method updates the level while the player is not on the ground. If the player is on a
     * platform, jump and remove the platform.
     * <p>
     * Check if player is on the ground.
     */
    @Test
    public void jumpFromPlatformOntoGround() {
        while (!player.isOnGround()) {
            level.updateDebug();
            if (player.isOnPlatform()) {
                player.jump();
                level.clearEntities();
            }
        }

        Assert.assertTrue(player.isOnGround());
    }
}
