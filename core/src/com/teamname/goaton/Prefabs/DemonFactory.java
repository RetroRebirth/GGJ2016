package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonFactory {
    public static GameObject Create()
    {
        GameObject go = new GameObject();
        go.addComponent(new GoatMovementComponent());
        go.addComponent(new StaticSpriteRenderComponent(new Sprite(new Texture(Gdx.files.internal("art/demon_down.png")))));
        go.addComponent(new GoatPhysicsComponent());
        go.addComponent(new EnemyComponent(1));
        go.tags.add("demon");
        return go;
    }

}
