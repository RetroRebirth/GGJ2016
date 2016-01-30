package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;

/**
 * Created by kpidding on 1/30/16.
 */
public class SpriteRenderComponent extends Component {
    protected Sprite sprite;
    public SpriteRenderComponent(Sprite sprite)
    {
        this.sprite = sprite;
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
    }

    @Override
    protected void update(float dt) {

        sprite.setPosition(gameObject.getPosition().x - sprite.getOriginX(),gameObject.getPosition().y - sprite.getOriginY());
    }

    @Override
    public String getID() {
        return "SpriteRenderComponent";
    }


    @Override
    protected void render(SpriteBatch sb) {

        sprite.draw(sb);
    }

    @Override
    public Component cloneComponent() {
        return new SpriteRenderComponent(new Sprite(sprite));
    }

}
