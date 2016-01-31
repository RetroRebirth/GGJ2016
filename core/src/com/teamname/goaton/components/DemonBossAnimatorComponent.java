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
    private float attackAnim = 0;
    private boolean active = true;
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
                Tween.to(head, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(3.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(jaw, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(3.0f)
                        .target(1.0f)
                        .ease(TweenEquations.easeOutCubic)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyr, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(3.4f)
                        .target(1.0f)
                        .ease(TweenEquations.easeInOutQuart)
                        .start(GoatonWorld.TweenManager);
                Tween.to(bodyl, SpriteAccessor.TWEEN_ALPHA,5.5f)
                        .delay(3.4f)
                        .target(  1.0f)
                        .ease(TweenEquations.easeInOutQuart)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                attackAnim = 5f;
                                ((EnemyComponent)gameObject.getComponent("EnemyComponent")).canBeDamaged = true;
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
                GoatonWorld.sendGlobalMessage(new Message("bossDamaged"));

            }
        });
        this.on("attackAnim", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                 attackAnim = (Float)msg.getArg();
            }
        });

        this.on("destroy", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                TweenCallback shakeCB = new TweenCallback() {
                    @Override
                    public void onEvent(int i, BaseTween<?> baseTween) {
                       GoatonWorld.sendGlobalMessage(new Message("cameraShake",new CamShakeControl(0.2f,45)));
                    }
                };
                active = false;
                float delay = 1.f;
                float headFadeOutLen = 3.f;
                head.setColor(Color.WHITE);
                jaw.setColor(Color.WHITE);
                Timeline.createSequence()
                        .beginParallel()
                        .push(Tween.to(head, SpriteAccessor.TWEEN_ROT, 1.5f)
                                .target(50))
                        .push(Tween.to(head,SpriteAccessor.TWEEN_XY,1.0f)
                        .target(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y - 120)
                        .setCallback(shakeCB))
                        .end()
                        .beginParallel()
                        .push(Tween.to(head, SpriteAccessor.TWEEN_XYSCALEXY, headFadeOutLen)
                                .target(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y, 0.1f * scale)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .push(Tween.to(head, SpriteAccessor.TWEEN_ALPHA, headFadeOutLen)
                                .target(0f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .end()

                        .start(GoatonWorld.TweenManager);
                Timeline.createSequence()
                        .beginParallel()
                        .push(Tween.to(jaw, SpriteAccessor.TWEEN_ROT, 1.5f)
                                .target(50))
                        .push(Tween.to(jaw,SpriteAccessor.TWEEN_XY,1.0f)
                                .target(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y - 120))

                        .end()
                        .beginParallel()
                        .push(Tween.to(jaw, SpriteAccessor.TWEEN_XYSCALEXY, headFadeOutLen)
                                .target(gameObject.getScreenPosition().x + 2, gameObject.getScreenPosition().y - 2 , 0.1f * scale)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .push(Tween.to(jaw, SpriteAccessor.TWEEN_ALPHA, headFadeOutLen)
                                .target(0f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .end()
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                GoatonWorld.sendGlobalMessage(new Message("cameraShake",new CamShakeControl(1.f,80.f)));
                            }
                        })

                        .start(GoatonWorld.TweenManager);




                Timeline.createSequence()

                        .push(Tween.to(bodyl, SpriteAccessor.TWEEN_XYROT, 1.0f)
                                .target(gameObject.getScreenPosition().x - 2.5f * bodyl.getWidth(), gameObject.getScreenPosition().y - 110, -10))


                        .beginParallel()
                        .push(Tween.to(bodyl, SpriteAccessor.TWEEN_XYSCALEXY, 3.5f)
                                .target(gameObject.getScreenPosition().x , gameObject.getScreenPosition().y + 10, 0.2f * scale/2)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .push(Tween.to(bodyl, SpriteAccessor.TWEEN_ALPHA, 3.5f)
                                .target(0f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .end()

                        .start(GoatonWorld.TweenManager);
                Timeline.createSequence()
                        .push(Tween.to(bodyr,SpriteAccessor.TWEEN_XYROT,1.0f)
                                .target(gameObject.getScreenPosition().x + 2.5f * bodyr.getWidth(), gameObject.getScreenPosition().y - 110,10))


                        .beginParallel()
                        .push(Tween.to(bodyr, SpriteAccessor.TWEEN_XYSCALEXY, 3.5f)
                                .target(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y + 10, 0.2f * scale/2)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .push(Tween.to(bodyr, SpriteAccessor.TWEEN_ALPHA, 3.5f)
                                .target(0f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(delay))
                        .end()

                        .start(GoatonWorld.TweenManager);
            }
        });
    }

    @Override
    protected void update(float dt) {
        if(active) {


            t += dt;
            float xOff = 0;
            if (damageTime > 0) {
                xOff = 4;
                damageTime -= dt;
                if (damageTime <= 0) {
                    head.setColor(Color.WHITE);
                    jaw.setColor((Color.WHITE));
                }
            }
            if (attackAnim <= 0) {


                head.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t), gameObject.getScreenPosition().y + (float) Math.sin(t) * headBob);
                jaw.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t + Math.PI), gameObject.getScreenPosition().y + (float) (Math.sin(t) * headBob) + (float) Math.sin(t * 1.05 + 0.5) * jawBob);
                bodyr.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) + 2.5f * bodyl.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
                bodyl.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) - 2.5f * bodyr.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
            } else {
                head.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t), gameObject.getScreenPosition().y);
                jaw.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t + Math.PI), gameObject.getScreenPosition().y - 35);
                bodyr.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) + 2.5f * bodyl.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
                bodyl.setPosition(gameObject.getScreenPosition().x + xOff * (float) Math.cos(30 * t) - 2.5f * bodyr.getWidth(), gameObject.getScreenPosition().y + (float) Math.sin(t + 0.25) * bodyBob - 45);
                attackAnim -= dt;
            }
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
