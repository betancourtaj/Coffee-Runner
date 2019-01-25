package main.java.coffeerun.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Entities.Particles.Particle;
import main.java.coffeerun.Level.EnergyBar;
import main.java.coffeerun.Utilities.Collider;

/**
 * Player class extends Entity
 */
@SuppressWarnings("ALL")
public class Player extends Entity {

    private float gravity = -.05f;      // Float for gravity value.
    private float ground;               // Float for ground value.
    private float velocity;             // Float for velocity value.
    private float time = 0.0f;          // Float for time value default 0.0f.
    private float trippedTime = 0.0f;
    private float rotation = 0.0f;
    private boolean isOnPlatform;       // Boolean for if player is on platform.
    private boolean isAsleep = false;   // Boolean for if player is asleep.
    private boolean isTripped = false;
    private boolean isPillow = false;
    private boolean canJump = true;
    private static int highScore = 0;   // Int for high score.
    private static int multiplier = 1;  // Int for multiplier to calculate high score.
    private Particle particle;

    /**
     * Player Constructor
     * <p>
     * This constructor takes in a ground value for the player, and a bounds for out of bounds.
     * It then creates a new position, size, hitbox and texture for the player.
     *
     * @param ground - ground for player to stand on
     * @param bounds - bounds for player
     */
    public Player(float ground, float bounds) {
        super(bounds);
        position = new Vector2(-25.0f, ground);
        size = new Vector2(10, 10);
        hitBox = new Rectangle(position.x + 2, position.y + 1, size.x - 2, size.y - 1);
        this.ground = ground;
        this.texture = new Texture("player.png");
        particle = new Particle(new Vector2(position.x, position.y), new Vector2(5, 5), new Texture("powerupparticle.png"));
    }

    /**
     * Player debug Constructor
     * <p>*
     * @param ground - ground for player
     * @param bounds - bounds for player
     */
    public Player(float ground, float bounds, Particle particle, boolean test) {
        super(bounds);
        position = new Vector2(-25.0f, ground);
        size = new Vector2(10, 10);
        hitBox = new Rectangle(position.x + 2, position.y + 1, size.x - 2, size.y - 1);
        this.ground = ground;
        this.particle = particle;
        //this.texture = new Texture("player.png");
    }


    /**
     * getTexture Method
     *
     * @return texture
     */
    @Override
    public Texture getTexture() {
        return texture;
    }

    public Particle getParticle() {
        return particle;
    }

    /**
     * update Method
     * <p>
     * This method updates the players hitbox. It also increments the payer's high score, checks
     * if the player is on a platform, on ground, or in the air and sets the velocity accordingly,
     * and checks if the player is asleep.
     *
     * @param deltaTime - time passed
     */
    @Override
    public void update(float deltaTime) {
        hitBox.setPosition(position.x, position.y);

        particle.update(deltaTime);

        rotation += 0.05;

        time += deltaTime / 1000.0f;
        if (time >= 1.0f) {
            highScore++;
            highScore *= multiplier;
            System.out.println("Score: " + highScore);
            time = 0.0f;
        }

        if(isTripped) {
            if(isPillow)
                setScrollValue(-0.2f);
            else
                setScrollValue(0.0f);
            rotation = 10;
            trip(deltaTime);
        }

        velocity += gravity;
        if (isOnPlatform() || isOnGround()) {
            velocity = 0.0f;
        }
        position.y += velocity;

        checkIfPlayerIsInGround();
    }

    /**
     * Trip method.
     *
     * This method causes the player to stop for a small amount of time.
     * @param deltaTime - time passed
     */
    private void trip(float deltaTime) {
        trippedTime += deltaTime / 1000;
        if(trippedTime >= 1.5) {
            setScrollValue(-0.5f);
            rotation = 0;
            isTripped = false;
            trippedTime = 0.0f;
            canJump = true;
            isPillow = false;
        }
    }

    /**
     * SetTripped Method
     *
     * This method sets the isTripped boolean to true.
     */
    public void setTripped() {
        isTripped = true;
    }

    /**
     * updateDebug Method
     * <p>
     */
    public void updateDebug() {
        hitBox.setPosition(position.x, position.y);

        highScore = highScore + (multiplier);

        velocity += gravity;
        if (isOnPlatform() || isOnGround()) {
            velocity = 0.0f;
        }
        position.y += velocity;

        if(isTripped) {
            if(isPillow)
                setScrollValue(-0.2f);
            else
                setScrollValue(0.0f);
            rotation = 10;
            trip(0.01f);
        }
        checkIfPlayerIsInGround();
    }

    /**
     * checkIfPlayerIsInGround Method
     * <p>
     * This method checks if the player is on the ground and if he is, it sets the position to the
     * ground.
     */
    private void checkIfPlayerIsInGround() {
        if (isOnGround()) {
            position.y = ground;
        }
    }

    /**
     * checkPlayerAsleep Method
     * <p>
     * This method checks if the energy bar is empty and if so it sets the isAsleep flag to true.
     */
    private void checkPlayerAsleep(EnergyBar e) {
        if (e.isEmpty()) {
            isAsleep = true;
        }
    }

    /**
     * getCollider Method
     *
     * @return new Collider object of this instance
     */
    @Override
    public Collider getCollider() {
        return new Collider(this);
    }

    /**
     * jump Method
     * <p>
     * This method checks if the player is able to jump (on ground or platform) and then increases
     * the position by .1 and sets the velocity to 1.0f. It then sets the isOnPlatform flag to false.
     */
    public void jump() {
        if(canJump) {
            if (isOnGround() || isOnPlatform()) {
                position.y += .1;
                velocity = 1.0f;
            }
            isOnPlatform = false;
        }
    }

    /**
     * isOnPlatform Method
     *
     * @return isOnPlatform
     */
    public boolean isOnPlatform() {
        return isOnPlatform;
    }

    /**
     * setOnPlatform Method
     * <p>
     * This method takes in a boolean value and sets the flag isOnPlatform equal to the value passed.
     *
     * @param value - boolean value
     */
    public void setOnPlatform(boolean value) {
        isOnPlatform = value;
    }

    /**
     * isOnGround Method
     *
     * @return true if position y is equal to or below ground; false otherwise.
     */
    public boolean isOnGround() {
        return position.y <= ground;
    }

    /**
     * isAsleep Method
     *
     * @return isAsleep
     */
    public boolean isAsleep(EnergyBar energyBar) {
        checkPlayerAsleep(energyBar);
        return isAsleep;
    }

    /**
     * isCollidingWith Method
     *
     * @param other - other entity in collision
     * @return true if this entity is colliding with other entity; false otherwise
     */
    public boolean isCollidingWith(Entity other) {
        return getCollider().collidesWith(other.getCollider());
    }

    /**
     * getHighScore Method
     *
     * @return highScore
     */
    public static int getHighScore() {
        return highScore;
    }

    /**
     * resetHighScore Method
     * <p>
     */
    public static void resetHighScore() {
        highScore = 0;
    }

    /**
     * getVelocity Method
     *
     * @return velocity
     */
    public float getVelocity() {
        return velocity;
    }

    /**
     * addPointsToHighScore Method
     * <p>
     * This method adds the amount of points specified to the highScore.
     *
     * @param points - points to add
     */
    public void addPointsToHighScore(int points) {
        highScore += points;
    }

    /**
     * increaseMultiplier Method
     * <p>
     * This method sets the high score multiplier value for the player.
     */
    public void increaseMultiplier() {
        multiplier++;
    }

    /**
     * GetMultiplier Method
     *
     * @return multiplier
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * DisableJump Method
     *
     * This method sets canJump to false.
     */
    public void disableJump() {
        canJump = false;
    }

    /**
     * IsTripped Method
     *
     * @return isTripped
     */
    public boolean isTripped() {
        return isTripped;
    }

    /**
     * SetPillow Method
     *
     * This method sets isPillow equal to true.
     */
    public void setPillow() { isPillow = true; }

}
