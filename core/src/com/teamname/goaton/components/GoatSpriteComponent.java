package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.Prefabs.Goat;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatSpriteComponent extends SpriteRenderComponent {

    private static final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GOLD};
    public GoatSpriteComponent(Sprite sprite) {
        super(sprite);
    }
    private float throwTimer = 0;
    @Override
    protected void create() {

        sprite.setColor(colors[GoatonWorld.Random.nextInt(colors.length)]);

        this.on("throw",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                throwTimer = Goat.THROWTIME;
            }
        });
        super.create();

    }

    @Override
    protected void update(float dt) {
        if(throwTimer > 0)
        {
            throwTimer -= dt;
            float offset = (float)Math.abs(Math.sin(  Math.PI*2 * (1 - throwTimer/Goat.THROWTIME)) * throwTimer);
            sprite.setScale(1.0f + 1.5f*offset);
            sprite.setPosition(gameObject.getPosition().x, gameObject.getPosition().y + 15 * offset );
        }
        else
        {
            sprite.setScale(1.0f);
        }
        super.update(dt);
    }

    @Override
    public Component cloneComponent() {
        return new GoatSpriteComponent(new Sprite(sprite));
    }
}
