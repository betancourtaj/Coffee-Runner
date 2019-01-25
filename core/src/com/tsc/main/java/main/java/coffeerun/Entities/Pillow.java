package main.java.coffeerun.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.java.coffeerun.Utilities.Collider;

@SuppressWarnings("SpellCheckingInspection")
public class Pillow extends Entity {

    public Pillow(float x, float y, float bounds){
        super(bounds);

        texture = new Texture("pillow.png");
        position = new Vector2(x,y);
        size = new Vector2(10,5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y + (size.y / 8));
    }
    public Pillow(float x, float y, float bounds, boolean test){
        super(bounds);

        //texture = new Texture("pillow.png");
        position = new Vector2(x,y);
        size = new Vector2(10,5);
        hitBox = new Rectangle(position.x, position.y, size.x, size.y + (size.y / 8));
    }

    public Texture getTexture(){
        return texture;
    }

    public void update(float deltaTime){
        scroll();
        hitBox.setPosition(position.x, position.y);
    }

    public Collider getCollider(){
        return new Collider(this);
    }

}
