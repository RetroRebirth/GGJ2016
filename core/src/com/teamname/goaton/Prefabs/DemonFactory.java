package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonFactory {
    public static GameObject Create(Vector2 pitPos)
    {
        GameObject go = new GameObject();
        go.addComponent(new GoatMovementComponent());
        go.addComponent(new StaticSpriteRenderComponent(new Sprite(new Texture(Gdx.files.internal("art/demon_down.png")))));
        go.addComponent(new DemonPhysicsComponent());
        go.addComponent(new EnemyComponent(3));
        go.addComponent(new DemonAuraComponent());
        go.addComponent(new DemonPositionComponent(pitPos));
        go.tags.add("demon");
        go.layer = Assets.ACTOR_LAYER;
        return go;
    }

}
