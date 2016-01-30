package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.*;
import com.teamname.goaton.Prefabs.GoatFactory;

import java.util.HashMap;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatAnimatedSpriteComponent extends AnimatedSpriteRenderComponent {

    private static final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GOLD};
    private float throwTimer = 0;
    private static final String firstSprite = Assets.goat_D;

    public GoatAnimatedSpriteComponent(HashMap<String, Sprite> sprites) {
        super(sprites, sprites.get(firstSprite));
    }

    @Override
    protected void create() {

//        currentSprite.setColor(colors[GoatonWorld.Random.nextInt(colors.length)]);

        this.on("throw",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                throwTimer = GoatFactory.THROWTIME;
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
            currentSprite.setScale(1.0f + 1.5f*offset);
            currentSprite.setPosition(gameObject.getPosition().x, gameObject.getPosition().y + 15 * offset );
        }
        else
        {
            currentSprite.setScale(1.0f);
        }
        super.update(dt);
    }

    @Override
    public Component cloneComponent() {
        return new GoatAnimatedSpriteComponent(this.sprites);
    }
}
