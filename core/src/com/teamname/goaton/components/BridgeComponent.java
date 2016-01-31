package com.teamname.goaton.components;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/31/16.
 */
public class BridgeComponent extends Component {
    private static final float RaiseAmnt = 35;
    Sprite spt;
    Vector2 position;
    float spawnDelay;
    public BridgeComponent(Sprite bridgeTile, Vector2 position, float spawnDelay) {
        this.spawnDelay = spawnDelay;
        this.spt = new Sprite(bridgeTile);
        this.position = new Vector2(position);
    }

    @Override
    protected void create() {
        super.create();
        gameObject.setPosition(position);
        spt.setPosition(gameObject.getScreenPosition().x,gameObject.getScreenPosition().y - RaiseAmnt);
        spt.scale(0.0f);
        spt.setAlpha(0f);
        Timeline.createParallel()
                .push(
        Tween.to(spt, SpriteAccessor.TWEEN_XYSCALEXY,1.4f)
                .ease(TweenEquations.easeInCubic)
                .target(gameObject.getScreenPosition().x, gameObject.getScreenPosition().y, 4.0f))
                .push(
                        Tween.to(spt,SpriteAccessor.TWEEN_ALPHA,1.4f)
                        .ease(TweenEquations.easeInCubic)
                        .target(0.8f))
                .delay(spawnDelay)
                .start(GoatonWorld.TweenManager);

    }

    @Override
    protected void render(SpriteBatch sb) {
        spt.draw(sb);
    }

    @Override
    protected void update(float dt) {
        super.update(dt);
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
