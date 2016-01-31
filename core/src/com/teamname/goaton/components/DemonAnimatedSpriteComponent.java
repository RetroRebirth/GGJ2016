package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.*;
import com.teamname.goaton.Prefabs.GoatFactory;
import com.teamname.goaton.Prefabs.PitDyingGoatFactory;

import java.util.HashMap;
import java.util.Map;

import static com.teamname.goaton.GoatonWorld.Destroy;

/**
 * Created by Chris Williams on 1/31/16.
 */
public class DemonAnimatedSpriteComponent extends AnimatedSpriteRenderComponent {

    private static final String firstSprite = Assets.demon_D;
    public static final float WALKTIME = 0.3f;

    private int walkCycle = 0;
    private float walkDuration = 0.0f;
    private int walkCycleLoop = 3;
    private GameObject.Direction dir = GameObject.Direction.DOWN;
    private GameObject.Direction prevDir = GameObject.Direction.DOWN;

    @Override
    public String getID() {
        return "DemonAnimatedSpriteComponent";
    }

    public DemonAnimatedSpriteComponent(HashMap<String, Sprite> sprites) {

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
        // Read which direction the goat is moving
        dir = this.gameObject.direction;
        // Keep track of which frame of the cycle we should render
        if (dir == GameObject.Direction.NONE) {
            walkCycle = 0;
            walkDuration = WALKTIME;
        } else {
            if (walkDuration < 0.0f) {
                // Increment frame and reset time counter
                walkCycle = ++walkCycle % walkCycleLoop;
                walkDuration = WALKTIME;
            } else {
                walkDuration -= dt;
            }
        }

        // Render the sprite based on the direction and walk cycle
        switch (prevDir) {
            case UP:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.demon_U);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.demon_UL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.demon_UR);
                        break;
                }
                break;
            case DOWN:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.demon_D);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.demon_DL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.demon_DR);
                        break;
                }
                break;
            case LEFT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.demon_L);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.demon_LL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.demon_LR);
                        break;
                }
                break;
            case RIGHT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.demon_R);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.demon_RL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.demon_RR);
                        break;
                }
                break;
            case NONE:
                break;
            default:
                break;
        }

        // Keep track of the previous direction so we can have the goats stand when they stop moving
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
        DemonAnimatedSpriteComponent cmp = new DemonAnimatedSpriteComponent(newSprites);
        cmp.currentSprite = new Sprite(this.currentSprite);
        cmp.dir = this.dir;
        return cmp;

    }
}
