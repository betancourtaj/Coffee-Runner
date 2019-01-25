package main.java.coffeerun.Level;

import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import main.java.coffeerun.Entities.Entity;
import main.java.coffeerun.Entities.Items.Coffee;
import main.java.coffeerun.Entities.Items.Espresso;
import main.java.coffeerun.Entities.Pillow;
import main.java.coffeerun.Entities.Platform;
import main.java.coffeerun.Entities.Player;
import main.java.coffeerun.Entities.TrashCan;
import main.java.coffeerun.MainGame;
import main.java.coffeerun.States.EndMenuState;
import main.java.coffeerun.Utilities.BackgroundBufferImage;
import main.java.coffeerun.Utilities.ScrollableObject;

/**
 * Level Class.
 */
public class Level {

    private List<BackgroundBufferImage> backgroundBufferImages;     // List of images for background.
    private List<Entity> entities;                                  // List of entities on screen.
    private Player player;                                          // Player
    private EnergyBar energyBar;                                    // Energy bar
    private float levelTime;                                        // Time for level
    private float outOfBounds;                                      // out of bounds for level.
    private boolean touchedToJump = false;                          // Boolean to check if tap held down during jump.
    private static MainGame mainGame;                               // Main game.

    /**
     * Level Constructor
     * <p>
     * This constructor initializes all the class fields, adds two buffer images to the list,
     * creates a new player and energy bar.
     *
     * @param game - main game copy
     */
    public Level(MainGame game) {
        mainGame = game;
        levelTime = 0.0f;
        outOfBounds = mainGame.getLeft();
        entities = new LinkedList<Entity>();

        backgroundBufferImages = new LinkedList<BackgroundBufferImage>();
        backgroundBufferImages.add(new BackgroundBufferImage(mainGame.getLeft(), mainGame.getBottom(), mainGame.getViewPortWidth(), mainGame.getViewPortHeight(), outOfBounds));
        backgroundBufferImages.add(new BackgroundBufferImage(mainGame.getRight(), mainGame.getBottom(), mainGame.getViewPortWidth(), mainGame.getViewPortHeight(), outOfBounds));

        player = new Player(mainGame.getBottom(), outOfBounds);
        energyBar = new EnergyBar();
        ScrollableObject.setScrollValue(-0.5f);
    }

    /**
     * Level debug Constructor
     * <p>
     */
    public Level(Player player, EnergyBar energyBar) {
        levelTime = 0.0f;
        entities = new LinkedList<Entity>();

        this.player = player;
        this.energyBar = energyBar;
    }

    /**
     * input Method
     * <p>
     * This method checks if the user taps the screen. If they tap the screen, the player jumps,
     * otherwise the player stays in his current position.
     */
    public void input() {
        if (mainGame.getInputManager().touched) {
            if (!touchedToJump) {
                player.jump();
                touchedToJump = true;
            }
        } else {
            if (touchedToJump) {
                touchedToJump = false;
            }
        }
    }

    /**
     * update Method
     * <p>
     * This method updates all the entities in the level. It also updates the player and energy bar.
     *
     * @param deltaTime - time passed
     */
    public void update(float deltaTime) {
        player.update(deltaTime);
        energyBar.update(deltaTime);
        levelTime += deltaTime / 1000.0f;

        generateEntitiesOverTime();
        updateBackgroundPositions();
        checkBackgroundOutOfBounds();
        updateEntities(deltaTime);
        Espresso.updateAllEspressos(deltaTime, energyBar);

        if (player.isAsleep(energyBar)) {
            mainGame.getStateManager().push(new EndMenuState(mainGame));
        }
    }


    /**
     * updateDebug Method
     * <p>
     */
    public void updateDebug() {
        player.updateDebug();

        updateEntitiesDebug();
        energyBar.update(1000.0f);

        if (player.isAsleep(energyBar)) {
            mainGame.getStateManager().push(new EndMenuState(mainGame));
        }
    }

    /**
     * generateEntity Method
     * <p>
     */
    public void generateEntity(Entity platform) {
        entities.add(platform);
    }

    /**
     * removeEntity Method
     * <p>
     */
    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    /**
     * ClearEntities Method
     *
     * This method clears the entities list.
     */
    public void clearEntities() { entities.clear(); }

    /**
     * checkBackgroundOutOfBounds Method
     * <p>
     * This method checks if the background buffer image has reached the left side of the screen
     * then removes it from the list.
     */
    private void checkBackgroundOutOfBounds() {
        if (backgroundBufferImages.get(0).isOutOfBounds()) {
            backgroundBufferImages.add(new BackgroundBufferImage(mainGame.getRight(), mainGame.getBottom(), mainGame.getViewPortWidth(), mainGame.getViewPortHeight(), outOfBounds));
            backgroundBufferImages.remove(0);
        }
    }

    /**
     * generateEntitiesOverTime Method
     * <p>
     * This method checks that the number of entities in the list is less than max, then it generates
     * new entities at their positions by calling the generateEntities method.
     */
    private void generateEntitiesOverTime() {
        // Maximum number of entities allowed.
        int MAX_ENTITIES = 6;
        if (entities.size() != MAX_ENTITIES) {
            if (levelTime >= 5.0f) {
                generateEntities();
                levelTime = 0.0f;
            }
        }
    }

    /**
     * updateBackgroundPositions Method
     * <p>
     * This method iterates through each buffered image in the list, then updates the position of each.
     */
    private void updateBackgroundPositions() {
        for (BackgroundBufferImage next : backgroundBufferImages) {
            next.update();
        }
    }

    /**
     * generateEntities Method
     * <p>
     * This method gets called every 5 seconds, and generates 4 entities into the level.
     */
    private void generateEntities() {
        generateEntity(new Platform(mainGame.getRight() + 2, mainGame.getBottom(), outOfBounds));
        generateEntity(new Platform(mainGame.getRight() + 50, mainGame.getBottom() + 7, outOfBounds));
        generateEntity(new Coffee(mainGame.getRight() + 2, -2, outOfBounds));
        generateEntity(new Espresso(mainGame.getRight() + 10, -2, outOfBounds));
        generateEntity(new Pillow(mainGame.getRight() + 50, mainGame.getBottom() + 12, outOfBounds));
        generateEntity(new TrashCan(new Vector2(mainGame.getRight() + 30, mainGame.getBottom()), outOfBounds));
    }

    /**
     * render Method
     * <p>
     * This method takes all the entities in the level and renders them using the main game spritebatch.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public void render() {
        mainGame.getSpriteBatch().begin();

        renderBackground();
        renderEntities();

        mainGame.getSpriteBatch().draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getSize().x, player.getSize().y);
        if(player.getParticle().isEnabled()) {
            mainGame.getSpriteBatch().draw(player.getParticle().getTexture(), player.getParticle().getPosition().x, player.getParticle().getPosition().y, player.getParticle().getSize().x, player.getParticle().getSize().y);
        }
        mainGame.getSpriteBatch().draw(energyBar.getTexture(), -41, 17, energyBar.getEnergy() / 2, 2);
        mainGame.getSpriteBatch().end();
    }

    /**
     * renderEntities Method
     * <p>
     * This method is called from the render method. This iterates through each entity in the list and
     * renders it.
     */
    private void renderEntities() {
        for (Entity next : entities) {
            mainGame.getSpriteBatch().draw(next.getTexture(), next.getPosition().x, next.getPosition().y, next.getSize().x, next.getSize().y);
        }
    }

    /**
     * renderBackground Method
     * <p>
     * This method is called from the render method. This iterates through each buffer image in the
     * list and renders it.
     */
    private void renderBackground() {
        Iterator<BackgroundBufferImage> backgroundBufferImageIterator = backgroundBufferImages.iterator();
        while (backgroundBufferImageIterator.hasNext()) {
            BackgroundBufferImage next = backgroundBufferImageIterator.next();
            mainGame.getSpriteBatch().draw(next.getTexture(), next.getPosition().x, next.getPosition().y, next.getSize().x, next.getSize().y);
        }
    }

    /**
     * updateEntities Method
     * <p>
     * This method checks if entities are out of bounds and collisions between the entities
     * and the character.
     *
     * @param deltaTime - time passed
     */
    private void updateEntities(float deltaTime) {
        if (entities.size() != 0 && entities.get(0).isOutOfBounds()) {
            entities.remove(0);
        }

        checkCollisions(deltaTime);
    }

    /**
     * updateEntitiesDebug Method
     * <p>
     */
    private void updateEntitiesDebug() {
        checkCollisionsDebug();
    }

    /**
     * checkCollisions Method
     * <p>
     * This method iterates through each entity in the list, then checks if the player is colliding
     * with the entity, if so then it sets the boolean collides equal to true, and collided with equal
     * to the entity that the iterator is currently on. This method then calls the doCollision method.
     *
     * @param deltaTime - time passed
     */
    private void checkCollisions(float deltaTime) {
        boolean collides = false;
        Entity collidedWith = null;

        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            Entity next = entityIterator.next();
            next.update(deltaTime);
            if (player.isCollidingWith(next)) {
                collides = true;
                collidedWith = next;
            }
        }

        doCollision(collides, collidedWith);
    }

    /**
     * checkCollisions Method
     * <p>
     */
    private void checkCollisionsDebug() {
        boolean collides = false;
        Entity collidedWith = null;

        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            Entity next = entityIterator.next();
            //next.update(deltaTime);
            if (player.isCollidingWith(next)) {
                collides = true;
                collidedWith = next;
            }
        }

        doCollision(collides, collidedWith);
    }

    /**
     * doCollision Method
     * <p>
     * This method takes the entity that was collided with the player and then does whatever that
     * entity is supposed to do to change the game.
     * <p>
     * i.e. colliding with coffee adds energy to energy bar and adds 5 points to high score.
     *
     * @param collides - boolean if collided
     * @param collidedWith - entity collided with
     */
    private void doCollision(boolean collides, Entity collidedWith) {
        if (collides) {
            if (collidedWith instanceof Coffee) {
                collideCoffee(collidedWith);
            } else if (collidedWith instanceof Espresso) {
                collideEspresso(collidedWith);
            } else if (collidedWith instanceof Platform) {
                collidePlatform();
            } else if(collidedWith instanceof TrashCan) {
                collideTrashcan(collidedWith);
            } else if (collidedWith instanceof Pillow){
                collidePillow(collidedWith);
            }
        } else {
            player.setOnPlatform(false);
        }
    }

    /**
     * CollidePillow Method
     *
     * This method handles the collision between the player and a Pillow.
     * @param collidedWith - entity collided with
     */
    private void collidePillow(Entity collidedWith) {
        player.setTripped();
        player.setPillow();
        removeEntity(collidedWith);
    }

    /**
     * CollideTrashcan Method
     *
     * This method handles the collision between the player and a Trashcan.
     * @param collidedWith - entity collided with
     */
    private void collideTrashcan(Entity collidedWith) {
        player.setTripped();
        removeEntity(collidedWith);
        player.disableJump();
    }

    /**
     * CollidePlatform Method
     *
     * This method handles the collision between the player and a Platform.
     */
    private void collidePlatform() {
        if (player.getVelocity() < 0) {
            player.setOnPlatform(true);
        }
    }

    /**
     * CollideEspresso Method
     *
     * This method handles the collision between the player and a Espresso.
     * @param collidedWith - entity collided with
     */
    private void collideEspresso(Entity collidedWith) {
        ((Espresso) collidedWith).enablePowerUp(energyBar);
        removeEntity(collidedWith);
        player.getParticle().enable(player.getPosition().y + player.getSize().y);
    }

    /**
     * CollideCoffee Method
     *
     * This method handles the collision between the player and a Coffee.
     * @param collidedWith - entity collided with
     */
    private void collideCoffee(Entity collidedWith) {
        energyBar.addEnergy();
        player.addPointsToHighScore(5);
        removeEntity(collidedWith);
        player.increaseMultiplier();
        player.getParticle().enable(player.getPosition().y + player.getSize().y);
    }
}