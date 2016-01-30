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
import java.util.Map;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatAnimatedSpriteComponent extends AnimatedSpriteRenderComponent {

    private static final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GOLD};
    private static final String firstSprite = Assets.goat_D;
    private float throwTimer = 0;
    private boolean onGround;

    public GoatAnimatedSpriteComponent(HashMap<String, Sprite> sprites) {

        super(sprites, sprites.get(firstSprite));
        for(Map.Entry<String,Sprite> e : sprites.entrySet())
        {
            Sprite s = e.getValue();
            s.setOrigin(s.getWidth()/2,s.getHeight()/2);
        }
    }

    @Override
    protected void create() {

//        currentSprite.setColor(colors[GoatonWorld.Random.nextInt(colors.length)]);
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                currentSprite.setScale(1.15f);
                onGround = false;
            }
        });
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
        GoatMovementComponent.Direction dir = ((GoatMovementComponent) this.gameObject.getComponent("GoatMovementComponent")).getDirection();

        switch (dir) {
        case UP:
            currentSprite = sprites.get(Assets.goat_U);
            break;
        case DOWN:
            currentSprite = sprites.get(Assets.goat_D);
            break;
        case LEFT:
            currentSprite = sprites.get(Assets.goat_L);
            break;
        case RIGHT:
            currentSprite = sprites.get(Assets.goat_R);
            break;
        case NONE:
            break;
        default:
            break;
        }

        if(throwTimer > 0)
        {
            throwTimer -= dt;
            float offset = (float)Math.abs(Math.sin(  Math.PI*2 * (1 - throwTimer/ GoatFactory.THROWTIME)) * throwTimer);
            currentSprite.setScale(1.0f + 1.5f*offset);
            currentSprite.setPosition(
                    gameObject.getPosition().x - currentSprite.getOriginX(),
                    gameObject.getPosition().y - currentSprite.getOriginY() + 15 * offset );
            if(throwTimer <=0)
            {
                gameObject.send(new Message("onGround"));
                onGround = true;
            }
        }
        else if(onGround)
        {
            currentSprite.setScale(1.0f);
        }
        super.update(dt);
    }

    @Override
    public Component cloneComponent() {
        HashMap<String, Sprite> newSprites = new HashMap<String, Sprite>();
        for(Map.Entry<String, Sprite> e : this.sprites.entrySet())
        {
            newSprites.put(e.getKey(),new Sprite(e.getValue()));
        }
        return new GoatAnimatedSpriteComponent(newSprites);

    }
}
