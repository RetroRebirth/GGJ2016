package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.*;
import com.teamname.goaton.Prefabs.GoatFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris Williams on 1/30/16.
 */
public class PlayerAnimatedSpriteComponent extends AnimatedSpriteRenderComponent {

    private static final String firstSprite = Assets.player_D;
    public static final float WALKTIME = 0.3f;

    GameObject.Direction dir = GameObject.Direction.NONE;
    private int walkCycle = 0;
    private float walkDuration = 0.0f;
    private int walkCycleLoop = 3;
    private GameObject.Direction prevDir = GameObject.Direction.NONE;

    @Override
    public String getID() {
        return "PlayerAnimatedSpriteComponent";
    }

    public PlayerAnimatedSpriteComponent(HashMap<String, Sprite> sprites) {

        super(sprites, sprites.get(firstSprite));
        for(Map.Entry<String,Sprite> e : sprites.entrySet())
        {
            Sprite s = e.getValue();
            s.setOrigin(s.getWidth()/2,s.getHeight()/2);
        }
    }

    @Override
    protected void create() {

        super.create();

    }



    @Override
    protected void update(float dt) {


        // Read which direction the player is moving
        dir = this.gameObject.direction;
        // Keep track of which frame of the cycle we should render
        if (dir == GameObject.Direction.NONE) {
            walkCycle = 0;
            walkDuration = 0.0f;
        } else {
            if (walkDuration >= WALKTIME) {
                walkCycle = ++walkCycle % walkCycleLoop;
                walkDuration = 0.0f;
            } else {
                walkDuration += dt;
            }
        }

        // TODO check for carry or normal sprite
        // Render the sprite based on the direction and walk cycle
        switch (prevDir) {
            case UP:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.player_U);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.player_UL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.player_UR);
                        break;
                }
                break;
            case DOWN:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.player_D);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.player_DL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.player_DR);
                        break;
                }
                break;
            case LEFT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.player_L);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.player_LL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.player_LR);
                        break;
                }
                break;
            case RIGHT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.player_R);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.player_RL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.player_RR);
                        break;
                }
                break;
            case NONE:
                break;
            default:
                break;
        }

        // Keep track of the previous direction so we can have the player stand when they stop moving
        if (dir != GameObject.Direction.NONE) {
            prevDir = dir;
        }

        super.update(dt);
    }

    @Override
    public Component cloneComponent() {
        HashMap<String, Sprite> newSprites = new HashMap<String, Sprite>();
        for(Map.Entry<String, Sprite> e : this.sprites.entrySet())
        {
            newSprites.put(e.getKey(),new Sprite(e.getValue()));
        }
        PlayerAnimatedSpriteComponent cmp = new PlayerAnimatedSpriteComponent(newSprites);
        cmp.currentSprite = new Sprite(this.currentSprite);
        cmp.dir = this.dir;
        return cmp;

    }
}
