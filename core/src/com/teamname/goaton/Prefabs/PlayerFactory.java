package com.teamname.goaton.Prefabs; /**
 * Created by pya on 1/30/16.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.Assets;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Input.ControllerInputSorce;
import com.teamname.goaton.Input.GameInputSource;
import com.teamname.goaton.Input.KeyboardInputSource;
import com.teamname.goaton.components.*;

import java.util.ArrayList;
import java.util.HashMap;

/* Components needed:
    Input
    Collision?
 */
public class PlayerFactory extends GameObject {
    public static GameObject Create() {
        GameObject go = new GameObject();
        GameInputSource src;
        if(Controllers.getControllers().size > 0)
        {
            src = new ControllerInputSorce(Controllers.getControllers().get(0));
        }
        else
        {
            src = new KeyboardInputSource();
        }
        go.addComponent(new PlayerMovementComponent(src));
        go.addComponent(new SpriteRenderComponent(
                new Sprite(new Texture(Gdx.files.internal(Assets.player)))));
        go.addComponent(new PlayerPhysicsComponent());
        go.addComponent(new PlayerPickupComponent());
        go.addComponent(new GoatStackComponent());
        go.tags.add("player");
        return go;
    }

    protected static HashMap<String, Sprite> LoadSprites() {
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
