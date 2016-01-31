package com.teamname.goaton.components;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.*;
import com.teamname.goaton.Prefabs.GoatFactory;
import com.teamname.goaton.Prefabs.PitDyingGoatFactory;

import java.util.HashMap;
import java.util.Map;

import static com.teamname.goaton.GoatonWorld.Destroy;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatAnimatedSpriteComponent extends AnimatedSpriteRenderComponent {

    private static final Color[] colors = {Color.GRAY,Color.CYAN,Color.GOLD,Color.WHITE};
    private static final String firstSprite = Assets.goat_D;
    public static final float WALKTIME = 0.3f;
    public static final float STRUGGLETIME = 0.1f;
    GameObject.Direction dir = GameObject.Direction.DOWN;
    private float throwTimer = 0;
    private boolean onGround = true;
    private boolean justLanded = false;

    private int walkCycle = 0;
    private float walkDuration = 0.0f;
    private int walkCycleLoop = 3;
    private GameObject.Direction prevDir = GameObject.Direction.DOWN;

    private Color color;

    @Override
    public String getID() {
        return "GoatAnimatedSpriteComponent";
    }

    public GoatAnimatedSpriteComponent(HashMap<String, Sprite> sprites) {

        super(sprites, sprites.get(firstSprite));
        for(Map.Entry<String,Sprite> e : sprites.entrySet())
        {
            Sprite s = e.getValue();
            s.setOrigin(s.getWidth()/2,s.getHeight()/2);
        }
    }

    @Override
    protected void create() {
        // Pick a color and color all the goat sprites
        color = colors[GoatonWorld.Random.nextInt(colors.length)];
        for (Map.Entry<String, Sprite> entry : sprites.entrySet()) {
            entry.getValue().setColor(color);
        }

        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                currentSprite.setScale(1.15f);
                onGround = false;
            }
        });
        this.on("throw",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                throwTimer = GoatFactory.THROWTIME;
            }
        });
        this.on("initialThrow", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                throwTimer = 2*GoatFactory.THROWTIME ;

            }
        });

        super.create();

    }



    @Override
    protected void update(float dt) {

        if(!GoatonWorld.TileAt(gameObject.getPosition()) && justLanded && onGround)
        {
            GameObject go = GameObject.Instantiate(PitDyingGoatFactory.Create(gameObject));
            go.setPosition(gameObject.getPosition());
            GoatonWorld.numGoats--;
            Destroy(gameObject);
            justLanded = false;
        }
        // Read which direction the goat is moving
        dir = this.gameObject.direction;
        // Keep track of which frame of the cycle we should render
        if (onGround && dir == GameObject.Direction.NONE) {
            walkCycle = 0;
            walkDuration = WALKTIME;
        } else {
            if (walkDuration < 0.0f) {
                // Increment frame and reset time counter
                walkCycle = ++walkCycle % walkCycleLoop;
                walkDuration = onGround ? WALKTIME : STRUGGLETIME;
            } else {
                walkDuration -= dt;
            }
        }

        // Render the sprite based on the direction and walk cycle
        switch (prevDir) {
            case UP:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.goat_U);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.goat_UL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.goat_UR);
                        break;
                }
                break;
            case DOWN:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.goat_D);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.goat_DL);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.goat_DR);
                        break;
                }
                break;
            case LEFT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.goat_L);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.goat_LI);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.goat_LO);
                        break;
                }
                break;
            case RIGHT:
                switch (walkCycle) {
                    case 0:
                        currentSprite = sprites.get(Assets.goat_R);
                        break;
                    case 1:
                        currentSprite = sprites.get(Assets.goat_RI);
                        break;
                    case 2:
                        currentSprite = sprites.get(Assets.goat_RO);
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

        if(throwTimer > 0)
        {
            throwTimer -= dt;
            float offset = (float)Math.abs(Math.sin(  Math.PI* (1 - throwTimer/ GoatFactory.THROWTIME)) * throwTimer);
            currentSprite.setScale(1.0f + 1.5f*offset);
            currentSprite.setPosition(
                    gameObject.getScreenPosition().x - currentSprite.getOriginX(),
                    gameObject.getScreenPosition().y - currentSprite.getOriginY() + 15 * offset );
            if(throwTimer <=0)
            {
                if(!GoatonWorld.TileAt(gameObject.getPosition()))
                {
                    GameObject go = GameObject.Instantiate(PitDyingGoatFactory.Create(gameObject));
                    go.setPosition(gameObject.getPosition());
                    GoatonWorld.numGoats--;
                    Destroy(gameObject);
                }
                gameObject.send(new Message("onGround"));
                onGround = true;
                justLanded = true;
            }
        }
        else if(onGround)
        {
            // Goat just landed

            currentSprite.setScale(1.0f);
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
        GoatAnimatedSpriteComponent cmp = new GoatAnimatedSpriteComponent(newSprites);
        cmp.currentSprite = new Sprite(this.currentSprite);
        cmp.dir = this.dir;
        return cmp;

    }
}
