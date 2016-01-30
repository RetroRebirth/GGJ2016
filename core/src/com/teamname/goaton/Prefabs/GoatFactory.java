package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.*;

import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class GoatFactory {
    public static final float THROWTIME = 0.75f;

    public static GameObject create() {
        GameObject go = new GameObject();
        go.addComponent(new GoatMovementComponent());
        go.addComponent(new GoatAnimatedSpriteComponent(loadSprites()));
        go.addComponent(new GoatPhysicsComponent());
        go.addComponent(new PositionComponent());
        return go;
    }

    protected static HashMap<String, Sprite> loadSprites() {
        HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

        sprites.put(Assets.goat_D, new Sprite(new Texture(Gdx.files.internal(Assets.goat_D))));
        sprites.put(Assets.goat_DL, new Sprite(new Texture(Gdx.files.internal(Assets.goat_DL))));
        sprites.put(Assets.goat_DR, new Sprite(new Texture(Gdx.files.internal(Assets.goat_DR))));
        sprites.put(Assets.goat_U, new Sprite(new Texture(Gdx.files.internal(Assets.goat_U))));
        sprites.put(Assets.goat_UL, new Sprite(new Texture(Gdx.files.internal(Assets.goat_UL))));
        sprites.put(Assets.goat_UR, new Sprite(new Texture(Gdx.files.internal(Assets.goat_UR))));
        sprites.put(Assets.goat_R, new Sprite(new Texture(Gdx.files.internal(Assets.goat_R))));
        sprites.put(Assets.goat_RI, new Sprite(new Texture(Gdx.files.internal(Assets.goat_RI))));
        sprites.put(Assets.goat_RO, new Sprite(new Texture(Gdx.files.internal(Assets.goat_RO))));
        sprites.put(Assets.goat_L, new Sprite(new Texture(Gdx.files.internal(Assets.goat_L))));
        sprites.put(Assets.goat_LI, new Sprite(new Texture(Gdx.files.internal(Assets.goat_LI))));
        sprites.put(Assets.goat_LO, new Sprite(new Texture(Gdx.files.internal(Assets.goat_LO))));

        return sprites;
    }
}
