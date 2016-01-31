package com.teamname.goaton.components;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
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
                                Tween.to(armSprite, SpriteAccessor.TWEEN_XY, 2.3f)
                                        .delay(flipped ? 0.5f : 0)
                                        .ease(TweenEquations.easeOutCubic)
                                        .target(targPosition.x, targPosition.y))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_RGB, 2.3f)
                                .ease(TweenEquations.easeOutCubic)
                                .delay(flipped ? 0.5f : 0)
                                .target(1, 1, 1))
                        .push(Tween.to(armSprite, SpriteAccessor.TWEEN_ALPHA, 2.3f)
                                .ease(TweenEquations.easeOutCubic)
                                .delay(flipped ? 0.5f : 0)
                                .target(1.f))
                        .end()
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
