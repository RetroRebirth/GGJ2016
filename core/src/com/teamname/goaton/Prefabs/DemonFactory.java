package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.*;

import java.util.HashMap;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonFactory {
    public static GameObject Create(Vector2 pitPos)
    {
        GameObject go = new GameObject();
        go.addComponent(new DemonMovementComponent());
        go.addComponent(new DemonAnimatedSpriteComponent(LoadSprites()));
        go.addComponent(new DemonPhysicsComponent());
        go.addComponent(new EnemyComponent(3));
        go.addComponent(new DemonAuraComponent());
        go.addComponent(new DemonPositionComponent(pitPos));
        go.tags.add("demon");
        go.layer = Assets.ACTOR_LAYER;
        return go;
    }

    protected static HashMap<String, Sprite> LoadSprites() {
        HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

        sprites.put(Assets.demon_D, new Sprite(new Texture(Gdx.files.internal(Assets.demon_D))));
        sprites.put(Assets.demon_DL, new Sprite(new Texture(Gdx.files.internal(Assets.demon_DL))));
        sprites.put(Assets.demon_DR, new Sprite(new Texture(Gdx.files.internal(Assets.demon_DR))));
        sprites.put(Assets.demon_U, new Sprite(new Texture(Gdx.files.internal(Assets.demon_U))));
        sprites.put(Assets.demon_UL, new Sprite(new Texture(Gdx.files.internal(Assets.demon_UL))));
        sprites.put(Assets.demon_UR, new Sprite(new Texture(Gdx.files.internal(Assets.demon_UR))));
        sprites.put(Assets.demon_R, new Sprite(new Texture(Gdx.files.internal(Assets.demon_R))));
        sprites.put(Assets.demon_RR, new Sprite(new Texture(Gdx.files.internal(Assets.demon_RR))));
        sprites.put(Assets.demon_RL, new Sprite(new Texture(Gdx.files.internal(Assets.demon_RL))));
        sprites.put(Assets.demon_L, new Sprite(new Texture(Gdx.files.internal(Assets.demon_L))));
        sprites.put(Assets.demon_LR, new Sprite(new Texture(Gdx.files.internal(Assets.demon_LR))));
        sprites.put(Assets.demon_LL, new Sprite(new Texture(Gdx.files.internal(Assets.demon_LL))));

        return sprites;
    }

}
