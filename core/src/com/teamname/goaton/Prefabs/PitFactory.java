package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.PitEnemyComponent;
import com.teamname.goaton.components.EnemyComponent;
import com.teamname.goaton.components.PitPhysicsComponent;
import com.teamname.goaton.components.PitPositionComponent;
import com.teamname.goaton.components.PitStaticSpriteComponent;


/**
 * Created by pya on 1/30/16.
 */

public class PitFactory {
    public static GameObject Create() {
        GameObject go = new GameObject();

        go.addComponent(new PitStaticSpriteComponent(new Sprite(new Texture(Gdx.files.internal(Assets.pit)))));
        go.addComponent(new PitPhysicsComponent());
        go.addComponent(new PitPositionComponent());
        //go.addComponent(new PitEnemyComponent(1));
//        go.addComponent(new PitEnemyComponent(1));
        go.layer = Assets.PIT_LAYER;

        return go;
    }


}
