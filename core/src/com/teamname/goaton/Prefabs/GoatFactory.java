package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.GoatMovementComponent;
import com.teamname.goaton.components.GoatPhysicsComponent;
import com.teamname.goaton.components.GoatStaticSpriteComponent;
import com.teamname.goaton.components.PositionComponent;

/**
 * Created by pya on 1/30/16.
 */
public class GoatFactory {
    public static final float THROWTIME = 0.75f;

    public static GameObject Create() {
        GameObject go = new GameObject();
        go.addComponent(new GoatMovementComponent());
        go.addComponent(new GoatStaticSpriteComponent(new Sprite(new Texture(Gdx.files.internal(Assets.goat)))));
        go.addComponent(new GoatPhysicsComponent());
        go.addComponent(new PositionComponent());
        return go;
    }
}
