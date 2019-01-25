package main.java.coffeerun.Level;

import com.badlogic.gdx.graphics.Texture;

/**
 * EnergyBar class
 */
public class EnergyBar {

    private Texture texture;                 // Texture for energy bar.
    private int energy = 100;                // Int energy default 100.
    private float time = 0.0f;               // Float time default 0.0f.
    private boolean isEmpty = false;         // Boolean isEmpty for if energy is zero.
    private boolean enabled = true;   // Boolean enabled for if energy bar is enabled.

    /**
     * EnergyBar Constructor
     * <p>
     * This constructor creates a new Texture for the EnergyBar object.
     */
    public EnergyBar() {
        texture = new Texture("progressBar.png");
    }

    /**
     * EnergyBar debug Constructor
     * <p>
     *
     * @param energy - amount of energy for player
     */
    public EnergyBar(int energy) {
        this.energy = energy;
        //texture = new Texture("blank.png");
    }

    public EnergyBar(int energy, boolean test) {
        this.energy = energy;
    }

    /**
     * update Method
     * <p>
     * This method decrements the energy if it is enabled, otherwise it leaves the value alone.
     *
     * @param deltaTime - time passed
     */
    public void update(float deltaTime) {
        if (enabled) {
            time += deltaTime / 1000.0f;
            if (time >= 0.5f) {
                if (energy <= 0) {
                    isEmpty = true;
                } else {
                    energy--;
                }
                time = 0.0f;
            }
        }
    }

    /**
     * getTexture Method
     *
     * @return texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * getEnergy Method
     *
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * isEmpty Method
     *
     * @return isEmpty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * disable Method
     * <p>
     * This method sets the enabled flag to false.
     */
    public void disable() {
        enabled = false;
    }

    /**
     * enable Method
     * <p>
     * This method sets the enabled flag to true.
     */
    public void enable() {
        enabled = true;
    }

    /**
     * isDisabled Method
     *
     * @return true if enabled equals false; false otherwise
     */
    public boolean isDisabled() {
        return !enabled;

    }

    /**
     * addEnergy Method
     * <p>
     * This method adds 5 energy or enough to get to 100.
     */
    public void addEnergy() {
        if (energy + 5 > 100) {
            energy = 100;
        } else {
            energy += 5;
        }
    }

    /**
     * makeFull Method
     * <p>
     * This method makes the energy bar full again.
     */
    public void makeFull() {
        energy = 100;
    }
}
