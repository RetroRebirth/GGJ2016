package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.Prefabs.GoatFactory;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatStaticSpriteComponent extends StaticSpriteRenderComponent {

    private static final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GOLD};
    public GoatStaticSpriteComponent(Sprite sprite) {
        super(sprite);
    }
    private float throwTimer = 0;
    private boolean held;
    @Override
    protected void create() {

        sprite.setColor(colors[GoatonWorld.Random.nextInt(colors.length)]);
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                sprite.setScale(1.25f);
                held = true;
            }
        });
        this.on("throw",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                throwTimer = GoatFactory.THROWTIME;
                held = false;
            }
        });
        super.create();

    }

    @Override
    protected void update(float dt) {
        if(throwTimer > 0)
        {
            throwTimer -= dt;
            float offset = (float)Math.abs(Math.sin(  Math.PI*2 * (1 - throwTimer/ GoatFactory.THROWTIME)) * throwTimer);
            sprite.setScale(1.0f + 1.5f*offset);
            sprite.setPosition(gameObject.getPosition().x, gameObject.getPosition().y + 15 * offset );
            if(throwTimer <= 0)
            {
                gameObject.send(new Message("onGround"));
            }
        }
        else if(!held)
        {
            sprite.setScale(1.0f);
        }
        super.update(dt);
    }

    @Override
    public Component cloneComponent() {
        return new GoatStaticSpriteComponent(new Sprite(sprite));
    }
}
