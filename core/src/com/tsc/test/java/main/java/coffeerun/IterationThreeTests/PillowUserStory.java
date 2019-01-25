package main.java.coffeerun.IterationThreeTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import coffeerun.GameTest;
import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Entities.Pillow;
import main.java.coffeerun.Entities.Platform;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Level.Level;
import main.java.coffeerun.Utilities.ScrollableObject;

public class PillowUserStory extends GameTest {

    private Level level;                // Level object for simulated level.
    private Player player;              // Player object for simulated player.
    private EnergyBar energyBar;        // Energy bar for simulated Energy bar.
    private Pillow collidePillow;       // Pillow for colliding.

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
        collidePillow = new Pillow(-20, -30, 0.0f, true);
        level = Mockito.spy(new Level(player, energyBar));

        player.jump();
        level.generateEntity(collidePillow);
    }

    /**
     * TestDecreaseSpeed Method
     *
     * This method tests if the player's speed decreases when they collide with a trashcan object.
     */
    @Test
    public void testDecreaseSpeed() {
        boolean slowed = false;
        while(!player.isOnGround()) {
            level.updateDebug();
            if(ScrollableObject.getScrollValue() > -0.5f) {
                slowed = true;
            }
        }

        Assert.assertTrue(slowed);
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
     * RemoveOverallPoints Method
     *
     * This method tests if the players points get reset after hitting a pillow object.
     */
    @Test
    public void removeOverallPoints() {
        Player.resetHighScore();
        level.updateDebug();
        int highScoreOrigin = Player.getHighScore();
        collidePillow = new Pillow(-25, -45, 0.0f, true);
        level.generateEntity(collidePillow);
        level.updateDebug();
        int updatedHighScore = Player.getHighScore();
        Assert.assertTrue(updatedHighScore <= highScoreOrigin + 5);
    }
}
