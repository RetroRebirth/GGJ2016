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
        go.addComponent(new PlayerAnimatedSpriteComponent(LoadSprites()));
//        go.addComponent(new SpriteRenderComponent(
//                new Sprite(new Texture(Gdx.files.internal(Assets.player)))));
        go.addComponent(new PlayerPhysicsComponent());
        go.addComponent(new PlayerPickupComponent());
        go.addComponent(new GoatStackComponent());
        go.tags.add("player");
        go.layer = Assets.ACTOR_LAYER;
        return go;
    }

    protected static HashMap<String, Sprite> LoadSprites() {
        HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

        sprites.put(Assets.player_D, new Sprite(new Texture(Gdx.files.internal(Assets.player_D))));
        sprites.put(Assets.player_DL, new Sprite(new Texture(Gdx.files.internal(Assets.player_DL))));
        sprites.put(Assets.player_DR, new Sprite(new Texture(Gdx.files.internal(Assets.player_DR))));
        sprites.put(Assets.player_L, new Sprite(new Texture(Gdx.files.internal(Assets.player_L))));
        sprites.put(Assets.player_LL, new Sprite(new Texture(Gdx.files.internal(Assets.player_LL))));
        sprites.put(Assets.player_LR, new Sprite(new Texture(Gdx.files.internal(Assets.player_LR))));
        sprites.put(Assets.player_R, new Sprite(new Texture(Gdx.files.internal(Assets.player_R))));
        sprites.put(Assets.player_RL, new Sprite(new Texture(Gdx.files.internal(Assets.player_RL))));
        sprites.put(Assets.player_RR, new Sprite(new Texture(Gdx.files.internal(Assets.player_RR))));
        sprites.put(Assets.player_U, new Sprite(new Texture(Gdx.files.internal(Assets.player_U))));
        sprites.put(Assets.player_UL, new Sprite(new Texture(Gdx.files.internal(Assets.player_UL))));
        sprites.put(Assets.player_UR, new Sprite(new Texture(Gdx.files.internal(Assets.player_UR))));

        sprites.put(Assets.playerC_D, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_D))));
        sprites.put(Assets.playerC_DL, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_DL))));
        sprites.put(Assets.playerC_DR, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_DR))));
        sprites.put(Assets.playerC_L, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_L))));
        sprites.put(Assets.playerC_LL, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_LL))));
        sprites.put(Assets.playerC_LR, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_LR))));
        sprites.put(Assets.playerC_R, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_R))));
        sprites.put(Assets.playerC_RL, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_RL))));
        sprites.put(Assets.playerC_RR, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_RR))));
        sprites.put(Assets.playerC_U, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_U))));
        sprites.put(Assets.playerC_UL, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_UL))));
        sprites.put(Assets.playerC_UR, new Sprite(new Texture(Gdx.files.internal(Assets.playerC_UR))));

        return sprites;
    }

}
