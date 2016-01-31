package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by kpidding on 1/30/16.
 */
public class SpriteRenderComponent extends Component {
    protected Sprite sprite;
    private boolean visible = true;

    public SpriteRenderComponent(Sprite sprite)
    {
        this.sprite = sprite;
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
    }

    @Override
    protected void update(float dt) {

        sprite.setPosition(gameObject.getScreenPosition().x - sprite.getOriginX(),
                            gameObject.getScreenPosition().y - sprite.getOriginY());
    }

    @Override
    public String getID() {
        return "SpriteRenderComponent";
    }


    @Override
    protected void render(SpriteBatch sb) {
        if(visible) {
            sprite.draw(sb);
        }
    }

    @Override
    public Component cloneComponent() {
        return new SpriteRenderComponent(new Sprite(sprite));
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
