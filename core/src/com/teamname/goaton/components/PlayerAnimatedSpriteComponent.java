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
    public static final float WALKTIME = 0.1f;

    private int walkCycle = 0;
    private float walkDuration = 0.0f;
    private int walkCycleLoop = 3;

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
        GameObject.Direction dir = this.gameObject.direction;

        // Keep track of which frame of the walking cycle we should render
        if (this.gameObject.getBody().getLinearVelocity().len() <= 0.0f) {
            // Player isn't moving, reset to standing sprite
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

        // Render the sprite based on the direction, walk cycle, and if you're carrying a goat
        if (((PlayerMovementComponent) this.gameObject.getComponent("PlayerMovementComponent")).holdingGoat) {
            switch (dir) {
                case UP:
                    switch (walkCycle) {
                        case 0:
                            currentSprite = sprites.get(Assets.playerC_U);
                            break;
                        case 1:
                            currentSprite = sprites.get(Assets.playerC_UL);
                            break;
                        case 2:
                            currentSprite = sprites.get(Assets.playerC_UR);
                            break;
                    }
                    break;
                case DOWN:
                    switch (walkCycle) {
                        case 0:
                            currentSprite = sprites.get(Assets.playerC_D);
                            break;
                        case 1:
                            currentSprite = sprites.get(Assets.playerC_DL);
                            break;
                        case 2:
                            currentSprite = sprites.get(Assets.playerC_DR);
                            break;
                    }
                    break;
                case LEFT:
                    switch (walkCycle) {
                        case 0:
                            currentSprite = sprites.get(Assets.playerC_L);
                            break;
                        case 1:
                            currentSprite = sprites.get(Assets.playerC_LL);
                            break;
                        case 2:
                            currentSprite = sprites.get(Assets.playerC_LR);
                            break;
                    }
                    break;
                case RIGHT:
                    switch (walkCycle) {
                        case 0:
                            currentSprite = sprites.get(Assets.playerC_R);
                            break;
                        case 1:
                            currentSprite = sprites.get(Assets.playerC_RL);
                            break;
                        case 2:
                            currentSprite = sprites.get(Assets.playerC_RR);
                            break;
                    }
                    break;
                case NONE:
                    break;
                default:
                    break;
            }
        } else {
            switch (dir) {
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
        }

        // Hit stun color shading
        if (((PlayerMovementComponent) gameObject.getComponent("PlayerMovementComponent")).dead) {
//            currentSprite.setColor(Color.WHITE);
            currentSprite = sprites.get(Assets.player_dead);
        } else if (((PlayerMovementComponent) gameObject.getComponent("PlayerMovementComponent")).hit) {
            currentSprite.setColor(Color.GRAY);
        } else {
            currentSprite.setColor(Color.WHITE);
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
        return cmp;

    }
}
