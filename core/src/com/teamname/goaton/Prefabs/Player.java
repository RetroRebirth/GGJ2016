package com.teamname.goaton.Prefabs; /**
 * Created by pya on 1/30/16.
 */

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
import com.teamname.goaton.components.SpriteRenderComponent;

import java.util.ArrayList;
import java.util.HashMap;

/* Components needed:
    Input
    Collision?
 */
public class Player extends GameObject {

    /* All goats held */
    private ArrayList<Goat> goatStack;
    private int goatCapacity = 1;

    public Player() {
        this.addComponent(new PlayerMovementComponent(new KeyboardInputSource()));
        this.addComponent(new SpriteRenderComponent(
                new Sprite(new Texture(Gdx.files.internal(Assets.player)))));
        this.addComponent(new PlayerPhysicsComponent());
    }

    /**
     * Created by pya on 1/30/16.
     */
    public static class HellHole extends GameObject {
        private int radius;
        public ArrayList<Color> GoatsWanted;

        public HellHole(HashMap<String, Component> comps) {
            components = new HashMap<String, Component>(comps);

            GoatRequests();
        }

        public void GoatRequests() {
            /* Creates GoatsWanted Array */
        }

        private ArrayList<Color> genRandColor(int num) {
            /* Generate random array of colors (goat requests) */
            return null;
        }



    }
}
