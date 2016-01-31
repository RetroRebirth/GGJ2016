package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;

import java.util.HashMap;

/**
 * Created by kpidding on 1/30/16.
 */
public class AnimatedSpriteRenderComponent extends Component {
    protected HashMap<String, Sprite> sprites;
    protected Sprite currentSprite;

    public AnimatedSpriteRenderComponent(HashMap<String, Sprite> sprites, Sprite currentSprite)
    {
        this.sprites = sprites;
        this.currentSprite = currentSprite;
    }

    @Override
    protected void update(float dt) {
        currentSprite.setPosition(gameObject.getPosition().x - currentSprite.getOriginX(),gameObject.getPosition().y  - currentSprite.getOriginY());
    }

    @Override
    public String getID() {
        return "AnimatedSpriteRenderComponent";
    }


    @Override
    protected void render(SpriteBatch sb) {
        currentSprite.draw(sb);
    }

    @Override
    public Component cloneComponent() {
        return new AnimatedSpriteRenderComponent(this.sprites, this.currentSprite);
    }

}
