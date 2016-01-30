package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;

/**
 * Created by kpidding on 1/30/16.
 */
public class StaticSpriteRenderComponent extends Component {
    protected Sprite sprite;
    public StaticSpriteRenderComponent(Sprite sprite)
    {
        this.sprite = sprite;
    }

    @Override
    protected void update(float dt) {
        sprite.setPosition(gameObject.getPosition().x,gameObject.getPosition().y);
    }

    @Override
    public String getID() {
        return "StaticSpriteRenderComponent";
    }


    @Override
    protected void render(SpriteBatch sb) {

        sprite.draw(sb);
    }

    @Override
    public Component cloneComponent() {
        return new StaticSpriteRenderComponent(new Sprite(sprite));
    }

}
