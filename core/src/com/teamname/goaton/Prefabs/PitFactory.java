package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.components.PitPhysicsComponent;
import com.teamname.goaton.components.PitPositionComponent;
import com.teamname.goaton.components.PitStaticSpriteComponent;

import java.util.Random;

/**
 * Created by pya on 1/30/16.
 */

public class PitFactory {
    public static GameObject Create() {
        GameObject go = new GameObject();
        go.addComponent(new PitStaticSpriteComponent(new Sprite(new Texture(Assets.pit))));
        go.addComponent(new PitPositionComponent());
        go.addComponent(new PitPhysicsComponent());

        return go;
    }


}
