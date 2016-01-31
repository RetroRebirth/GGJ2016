package com.teamname.goaton.components;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonBossAnimatorComponent extends Component {

    private Sprite head;
    private Sprite jaw;
    private Sprite bodyl;
    private Sprite bodyr;
    private float scale = 5.5f;
    private float headBob = 20;
    private float jawBob = 10;
    private float bodyBob = 3;
    private  float t;
    private float damageTime = 0;
    public DemonBossAnimatorComponent()
    {
        head = new Sprite(new Texture(Gdx.files.internal("art/BossDemon/l1_demonKing_1.png")));
        jaw = new Sprite(new Texture(Gdx.files.internal("art/BossDemon/l2_demonKing_1.png")));
        bodyl = new Sprite(new Texture(Gdx.files.internal("art/demonboss_body.png")));
        bodyr = new Sprite(new Texture(Gdx.files.internal("art/demonboss_body.png")));
        bodyr.setFlip(true,false);

        head.scale(scale);
        jaw.scale(scale);
        bodyr.scale(scale/2);
        bodyl.scale(scale/2);
        head.setAlpha(0);
        jaw.setAlpha(0);
        bodyr.setAlpha(0);
        bodyl.setAlpha(0);


    }

    @Override
    protected void create() {
        this.on("spawnBoss", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                Tween.to(head, SpriteAccessor.TWEEN_ALPHA,3.5f)
                        .delay(1.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(jaw, SpriteAccessor.TWEEN_ALPHA,3.5f)
                        .delay(1.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyr, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(1.4f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyl, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(1.4f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
            }
        });
        this.on("damaged", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                damageTime = 0.25f;
                head.setColor(Color.BLUE);
                jaw.setColor(Color.BLUE);

            }
        });
    }

    @Override
    protected void update(float dt) {
        t += dt;
        float xOff = 0;
        if(damageTime > 0)
        {
            xOff = 4;
            damageTime -= dt;
            if(damageTime <= 0)
            {
                head.setColor(Color.WHITE);
                jaw.setColor((Color.WHITE));
            }
        }
        head.setPosition(gameObject.getScreenPosition().x + xOff*(float)Math.cos(30*t), gameObject.getScreenPosition().y + (float)Math.sin(t)*headBob);
        jaw.setPosition(gameObject.getScreenPosition().x+xOff*(float)Math.cos(30*t + Math.PI), gameObject.getScreenPosition().y + (float)(Math.sin(t)*headBob) + (float)Math.sin(t*1.05 + 0.5) * jawBob);
        bodyr.setPosition(gameObject.getScreenPosition().x+xOff*(float)Math.cos(30*t) + 2.5f*bodyl.getWidth(), gameObject.getScreenPosition().y + (float)Math.sin(t + 0.25) * bodyBob - 45);
        bodyl.setPosition(gameObject.getScreenPosition().x+xOff*(float)Math.cos(30*t) - 2.5f*bodyr.getWidth(), gameObject.getScreenPosition().y + (float)Math.sin(t + 0.25) * bodyBob - 45);

    }

    @Override
    protected void render(SpriteBatch sb) {
        bodyr.draw(sb);
        bodyl.draw(sb);

        head.draw(sb);
        jaw.draw(sb);


    }

    public String getID() {
        return "DemonBossAnimatorComponent";
    }



    @Override
    public Component cloneComponent() {
        return new DemonBossAnimatorComponent();
    }
}
