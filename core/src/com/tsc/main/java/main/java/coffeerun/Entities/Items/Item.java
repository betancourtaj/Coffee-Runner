package main.java.coffeerun.Entities.Items;

import main.java.coffeerun.Entities.Entity;

/**
 * Abstract Item class extends Entity
 */
public abstract class Item extends Entity {

    /**
     * Item Constructor
     * <p>
     * This constructor takes in a bounds for out of bounds and then calls the entity constructor
     * with that parameter.
     *
     * @param bounds - bounds for level
     */
    public Item(float bounds) {
        super(bounds);
    }


}
