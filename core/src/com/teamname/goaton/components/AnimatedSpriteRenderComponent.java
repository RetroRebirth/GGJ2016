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

    private boolean visible = true;

    public AnimatedSpriteRenderComponent(HashMap<String, Sprite> sprites, Sprite currentSprite)
    {
        this.sprites = sprites;
        this.currentSprite = currentSprite;
    }

    @Override
    protected void update(float dt) {
        currentSprite.setPosition(gameObject.getScreenPosition().x - currentSprite.getOriginX(),gameObject.getScreenPosition().y  - currentSprite.getOriginY());
    }

    @Override
    public String getID() {
        return "AnimatedSpriteRenderComponent";
    }


    @Override
    protected void render(SpriteBatch sb) {
        if(visible)
        currentSprite.draw(sb);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    @Override
    public Component cloneComponent() {
        return new AnimatedSpriteRenderComponent(this.sprites, this.currentSprite);
    }

}
