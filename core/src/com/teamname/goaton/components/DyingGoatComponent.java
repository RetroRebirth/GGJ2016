package com.teamname.goaton.components;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/30/16.
 */
public class DyingGoatComponent extends Component {
    Sprite goatSprite;
    private DyingGoatComponent(Sprite spr)
    {
        goatSprite = spr;
    }
    public DyingGoatComponent(Component goatAnimatedSpriteComponent) {
        goatSprite = new Sprite(((GoatAnimatedSpriteComponent)goatAnimatedSpriteComponent).currentSprite);
    }
    @Override
    protected void render(SpriteBatch sb) {
        goatSprite.draw(sb);
    }
    @Override
    protected void create() {

        Timeline.createSequence()
                .beginParallel()
                .push(Tween.to(goatSprite, SpriteAccessor.TWEEN_XYSCALEXY,0.75f)

                .target(gameObject.getScreenPosition().x - goatSprite.getOriginX(),
                        gameObject.getScreenPosition().y - goatSprite.getOriginY(),
                        0)
                .ease(TweenEquations.easeOutCirc)
                )
                .push(Tween.to(goatSprite,SpriteAccessor.TWEEN_ROT,0.75f)
                        .target(360)
                        .ease(TweenEquations.easeOutCirc))
                .end()
                .setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int i, BaseTween<?> baseTween) {
                        if(i == TweenCallback.END)
                        {
                            GoatonWorld.Destroy(gameObject);
                        }
                    }
                })
                .start(GoatonWorld.TweenManager);

    }


    @Override
    public String getID() {
        return "DyingGoatComponent";
    }

    @Override
    public Component cloneComponent() {
        return new DyingGoatComponent(new Sprite(goatSprite));
    }
}
