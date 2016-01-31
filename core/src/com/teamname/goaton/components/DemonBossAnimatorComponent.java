package com.teamname.goaton.components;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.*;
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
    private float attackAnim = 10;
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
                Tween.to(head, SpriteAccessor.TWEEN_ALPHA,8.5f)
                        .delay(5.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(jaw, SpriteAccessor.TWEEN_ALPHA,8.5f)
                        .delay(5.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyr, SpriteAccessor.TWEEN_ALPHA,8.5f)
                        .delay(5.4f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyl, SpriteAccessor.TWEEN_ALPHA,8.5f)
                        .delay(5.4f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                attackAnim = 5f;
                                GoatonWorld.sendGlobalMessage(new Message("cameraShake",new CamShakeControl(4.5f,35f)));
                            }
                        })
                        .start(GoatonWorld.TweenManager);
            }
        });
        this.on("damaged", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                damageTime = 0.25f;
                head.setColor(Color.BLUE);
                jaw.setColor(Color.BLUE);
                GoatonWorld.sendGlobalMessage(new Message("cameraShake", new CamShakeControl(0.1f,25f)));

            }
        });
        this.on("attackAnim", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                attackAnim = (Float)msg.getArg();
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
        if(attackAnim <= 0) {


            head.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t), gameObject.getScreenPosition().y + (float) Math.sin(t) * headBob);
            jaw.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t + Math.PI), gameObject.getScreenPosition().y + (float) (Math.sin(t) * headBob) + (float) Math.sin(t * 1.05 + 0.5) * jawBob);
            bodyr.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) + 2.5f * bodyl.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
            bodyl.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) - 2.5f * bodyr.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
        }
        else
        {
            head.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t), gameObject.getScreenPosition().y);
            jaw.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t + Math.PI), gameObject.getScreenPosition().y - 35);
            bodyr.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) + 2.5f * bodyl.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
            bodyl.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) - 2.5f * bodyr.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
            attackAnim -= dt;
        }
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
