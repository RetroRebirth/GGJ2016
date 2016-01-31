package com.teamname.goaton.components;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.teamname.goaton.*;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/31/16.
 */
public class BossArmComponent extends Component {
    Sprite armSprite;
    Vector2 targPosition;
    boolean flipped;

    public BossArmComponent(Sprite sp, Vector2 pos, boolean flipped)
    {
        armSprite = new Sprite(sp);
        armSprite.scale(2f);
        targPosition = new Vector2(pos);
        this.flipped = flipped;
        armSprite.setFlip(this.flipped,false);
        armSprite.setColor(Color.CLEAR);
        armSprite.setPosition(pos.x + 256 * (flipped? 1 : -1) , pos.y);
    }

    @Override
    protected void render(SpriteBatch sb) {
        armSprite.draw(sb);
    }

    @Override
    protected void create() {
        armSprite.setColor(Color.BLACK);
        super.create();
        this.on("spawnBoss", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                Timeline.createSequence()
                        .beginParallel()
                        .push(
                                Tween.to(armSprite, SpriteAccessor.TWEEN_XY, 1.7f)
                                        .delay(flipped ? 1.5f : 0)
                                        .ease(TweenEquations.easeInExpo)
                                        .target(targPosition.x, targPosition.y))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_RGB, 1.7f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(flipped ? 1.5f : 0)
                                .target(1, 1, 1))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_ALPHA, 1.7f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(flipped ? 1.5f : 0)
                                .target(1.f))
                        .end()
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                GoatonWorld.sendGlobalMessage(new Message("cameraShake", new CamShakeControl(0.05f, 15f)));
                            }
                        })
                        .start(GoatonWorld.TweenManager);
            }});
        this.on("destroy",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                float armDelay = 2.5f;
                Timeline.createSequence()
                        .beginParallel()
                        .push(
                                Tween.to(armSprite, SpriteAccessor.TWEEN_XY, 1.7f)
                                        .delay(flipped ? armDelay : armDelay + 0.2f)
                                        .ease(TweenEquations.easeInExpo)
                                        .target(targPosition.x  + 64 * (flipped? 1.15f : -1), targPosition.y + 100))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_RGB, 1.7f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(flipped ? armDelay : armDelay + 0.2f)
                                .target(0, 0, 0))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_ALPHA, 1.7f)
                                .ease(TweenEquations.easeInExpo)
                                .delay(flipped ? armDelay : armDelay + 0.2f)
                                .target(0.f))
                        .end()
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                GoatonWorld.sendGlobalMessage(new Message("cameraShake", new CamShakeControl(0.45f, 15f)));
                            }
                        })
                        .start(GoatonWorld.TweenManager);
            }});
        }

    @Override
    public String getID() {
        return "BossArmComponent" + (flipped ? "_r" : "");
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
