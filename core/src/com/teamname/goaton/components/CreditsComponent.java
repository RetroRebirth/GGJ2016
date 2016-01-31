package com.teamname.goaton.components;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/31/16.
 */
public class CreditsComponent extends Component {
    Sprite black;
    Sprite credits;
    boolean render = false;

    @Override
    protected void create() {
        black = new Sprite(new Texture("art/black.png"));
        black.setScale(200);
        black.setAlpha(0);
        credits = new Sprite(new Texture("art/Credits.png"));
        credits.setPosition(-credits.getOriginX(),-credits.getHeight() - 200);
        credits.setAlpha(0);
        on("gameFinish", new MsgHandler() {
            @Override

            public void handle(Message msg) {
                credits.setAlpha(1.0f);
                render = true;
                Tween.to(black, SpriteAccessor.TWEEN_ALPHA, 0.5f)
                        .target(1.0f)
                        .start(GoatonWorld.TweenManager);
                Tween.to(credits, SpriteAccessor.TWEEN_XY, 20.f)
                        .target(credits.getX(), -100)
                        .delay(0.25f)
                        .start(GoatonWorld.TweenManager);
            }

        });
    }

    @Override
    protected void render(SpriteBatch sb) {
        if(render)
        {
            black.draw(sb);
            credits.draw(sb);
        }
    }

    @Override
    public String getID() {
        return "CreditsComponent";
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
