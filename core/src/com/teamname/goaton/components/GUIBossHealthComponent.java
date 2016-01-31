package com.teamname.goaton.components;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.teamname.goaton.*;
import com.teamname.goaton.TweenWrappers.SpriteAccessor;

/**
 * Created by kpidding on 1/31/16.
 *
 */

public class GUIBossHealthComponent extends Component {


    //Resolution is 1280x720
    static final int GUI_WIDTH = 300;
    private float GUI_Y = -150f;
    static final float GUI_X_BEGIN = -1280/8.f;

    private int maxHP = 20;
    private float hp = 20;
    Sprite leftSprite;
    Sprite rightSprite;
    Sprite emptyBar;
    Sprite fullBar;

    public GUIBossHealthComponent() {
        Texture fullSprite = new Texture(Gdx.files.internal(Assets.healthBar));

        TextureAtlas atlas = new TextureAtlas();
        atlas.addRegion("leftEdge", fullSprite, 0, 0, 5, 16);
        atlas.addRegion("fullBar", fullSprite, 5, 0, 3, 16);
        atlas.addRegion("emptyBar", fullSprite, 8, 0, 3, 16);
        atlas.addRegion("rightEdge", fullSprite, 11, 0, 5, 16);

        leftSprite = atlas.createSprite("leftEdge");
        leftSprite.setOrigin(0, 0);
        leftSprite.setPosition(GUI_X_BEGIN, GUI_Y);
        leftSprite.setY(-200);
        fullBar = atlas.createSprite("fullBar");
        fullBar.setOrigin(0, 0);
        fullBar.setY(-200);
        emptyBar = atlas.createSprite("emptyBar");
        emptyBar.setOrigin(0, 0);
        emptyBar.setSize(0, 32);
        emptyBar.setY(-200);
        fullBar.setSize(GUI_WIDTH - 10, 32);
        rightSprite = atlas.createSprite("rightEdge");
        rightSprite.setPosition(GUI_X_BEGIN + GUI_WIDTH - 5, GUI_Y);
        rightSprite.setY(-200);
    }

    @Override
    protected void create() {
        this.on("spawnBoss",new MsgHandler() {
                @Override
                public void handle(Message msg) {
                    Timeline.createParallel()
                            .push(Tween.to(fullBar, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(fullBar.getX(), GUI_Y)
                                    .delay(8.5f))
                            .push(Tween.to(emptyBar, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(emptyBar.getX(), GUI_Y)
                                    .delay(8.5f))

                            .push(Tween.to(leftSprite, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(leftSprite.getX(), GUI_Y)
                                    .delay(8.5f))

                            .push(Tween.to(rightSprite, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(rightSprite.getX(), GUI_Y)
                                    .delay(8.5f))
                            .start(GoatonWorld.TweenManager);

                }
            });
        this.on("bossDamaged",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                hp--;
                if(hp <= 0)
                {
                    Timeline.createParallel()
                            .push(Tween.to(fullBar, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(fullBar.getX(), -200)
                                    .delay(3.5f))
                            .push(Tween.to(emptyBar, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(emptyBar.getX(), -200)
                                    .delay(3.5f))

                            .push(Tween.to(leftSprite, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(leftSprite.getX(), -200)
                                    .delay(3.5f))

                            .push(Tween.to(rightSprite, SpriteAccessor.TWEEN_XY, 1.f)
                                    .target(rightSprite.getX(), -200)
                                    .delay(3.5f))
                            .start(GoatonWorld.TweenManager);
                }

            }
        });
    }


    @Override
    protected void update(float dt) {
        super.update(dt);
        emptyBar.setPosition(GUI_X_BEGIN + (GUI_WIDTH - 5) * (float) hp / maxHP, emptyBar.getY());
        fullBar.setPosition(GUI_X_BEGIN + 5,fullBar.getY());

        emptyBar.setSize((GUI_WIDTH) * (1 - (float) hp / maxHP), 16);
        fullBar.setSize((GUI_WIDTH-10)*((float)hp/maxHP),16);

    }

    @Override
    protected void render(SpriteBatch sb) {
        fullBar.draw(sb);
        emptyBar.draw(sb);
        leftSprite.draw(sb);
        rightSprite.draw(sb);
    }

    @Override
    public String getID() {
        return "GuiBossHealthComponent";
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
