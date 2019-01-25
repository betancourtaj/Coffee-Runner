package main.java.coffeerun.IterationTwoTests;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.junit.Test;

import coffeerun.GameTest;
import main.java.coffeerun.Entities.Items.Coffee;
import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Level.Level;

/**
 * CoffeeUserStory Class extends GameTest
 */
public class CoffeeUserStory extends GameTest {

    private Level level;            // Level object for level.
    private Player player;          // Player object for player.
    private EnergyBar energyBar;    // EnergyBar object for energy bar.
    private Coffee coffee;          // Coffee object for coffee.

    /**
     * setUp Method
     * <p>
     * This method sets up the environment for the following tests
     */
    @Before
    public void setUp() {
        player = new Player(-45, 0.0f, Mockito.mock(Particle.class), true);
        energyBar = new EnergyBar(50);
        coffee = new Coffee(-25, -30, 0.0f, true);
        level = Mockito.spy(new Level(player, energyBar));

    }

    /**
     * addEnergyToEnergyBarTest Method
     * <p>
     * This test method is the test for the first user story scenario on the Coffee issue.
     * <p>
     * The player jumps, the level generates a coffee object above the player, and while the player
     * is not on the ground, continue to update the level.
     * <p>
     * After the player has landed, check that the energy bar is now at the correct value.
     * <p>
     * Coffee adds 5 to energy bar.
     */
    @Test
    public void addEnergyToEnergyBarTest() {
        player.jump();
        level.generateEntity(coffee);
        while (!player.isOnGround()) {
            level.updateDebug();
        }

        Assert.assertEquals(15, energyBar.getEnergy());
    }

    /**
     * multiplierTest Method
     * <p>
     * This test method is the test for the second user story scenario on the Coffee issue.
     * <p>
     * The player jumps, the level generates a coffee object above the player, and while the player
     * is not on the ground, continue to update the level.
     * <p>
     * After the player has landed, check that the multiplier is now at the correct value.
     * <p>
     * Coffee increments the multiplier by 1.
     */
    @Test
    public void multiplierTest() {
        player.jump();
        level.generateEntity(coffee);
        while (!player.isOnGround()) {
            level.updateDebug();
        }

        Assert.assertEquals(2, player.getMultiplier());
    }

    /**
     * addOverallPoints Method
     * <p>
     * This test method is the test for the third user story scenario on the Coffee issue.
     * <p>
     * updateDebug the level to get an origin high score, then generate a coffee object on top of the player
     * to simulate the player grabbing a coffee item, then update the level one more time to get
     * the updated high score.
     * <p>
     * Check that the second high score is more than the first high score by more than 5.
     */
    @Test
    public void addOverallPoints() {
        Player.resetHighScore();
        level.updateDebug();
        int highScoreOrigin = Player.getHighScore();
        coffee = new Coffee(-25, -45, 0.0f, true);
        level.generateEntity(coffee);
        level.updateDebug();
        int updatedHighScore = Player.getHighScore();
        Assert.assertTrue(updatedHighScore >= highScoreOrigin + 5);

    }
}
