package com.teamname.goaton.Prefabs; /**
 * Created by pya on 1/30/16.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Input.KeyboardInputSource;
import com.teamname.goaton.components.PlayerMovementComponent;
import com.teamname.goaton.components.PlayerPhysicsComponent;
import com.teamname.goaton.components.PlayerPickupComponent;
import com.teamname.goaton.components.SpriteRenderComponent;

import java.util.ArrayList;
import java.util.HashMap;

/* Components needed:
    Input
    Collision?
 */
public class PlayerFactory extends GameObject {
    public static GameObject Create() {
        GameObject go = new GameObject();
        go.addComponent(new PlayerMovementComponent(new KeyboardInputSource()));
        go.addComponent(new SpriteRenderComponent(
                new Sprite(new Texture(Gdx.files.internal(Assets.player)))));
        go.addComponent(new PlayerPhysicsComponent());
        go.addComponent(new PlayerPickupComponent());

        return go;
    }

}
