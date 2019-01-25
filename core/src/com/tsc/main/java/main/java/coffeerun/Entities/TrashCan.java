package main.java.coffeerun.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.Collider;

public class TrashCan extends Entity {

    public TrashCan(Vector2 position, float bounds) {
        super(bounds);
        this.position = position;
        this.size = new Vector2(5, 5);
        this.texture = new Texture("trashcan.png");
        hitBox = new Rectangle(position.x, position.y, size.x, size.y);
    }

    public TrashCan(float x, float y, float bounds, boolean test){
        super(bounds);

        //texture = new Texture("pillow.png");
        position = new Vector2(x,y);
        size = new Vector2(10,5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y + (size.y / 8));
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void update(float deltaTime) {
        position.x += xScroll;
        hitBox.setPosition(position.x, position.y);
    }

    @Override
    public Collider getCollider() {
        return new Collider(this);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }
}
