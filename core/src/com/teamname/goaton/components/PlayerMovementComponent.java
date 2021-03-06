package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.teamname.goaton.*;
import com.teamname.goaton.Input.GameInputSource;

/**
 * Created by kpidding on 1/30/16.
 */
public class PlayerMovementComponent extends Component {
    private GameInputSource src;
    public float speed = 10.0f;
    public PlayerMovementComponent(GameInputSource src)
    {
        this.src = src;
    }
    public boolean holdingGoat = false;
    public boolean hit = false;
    public boolean dead = false;
    private final float HIT_STUN = 1.0f;
    private float hitStun = HIT_STUN;

    @Override
    protected void create() {
        this.on("pickupGoat",new MsgHandler() {
                @Override
                public void handle(Message msg) {
                    holdingGoat = true;
                }
        });

        this.on("player_dead",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                hit = false;
                dead = true;
            }
        });
    }

    @Override
    public Component cloneComponent() {
        return new PlayerMovementComponent(src);
    }

    @Override
    protected void update(float dt) {
        if (dead) {
            // Do nothing because you died
        } else if (hit) {
            // TODO Knockback animation
            hitStun -= dt;
            if (hitStun < 0.0f) {
                hit = false;
                hitStun = HIT_STUN;
            }
        }

        if (!dead && (!hit || hitStun < 2.f*HIT_STUN / 3.f)) {
            Vector2 movement = new Vector2();
            movement.x += src.getMovementOnAxis(GameInputSource.Axis.X_AXIS);
            movement.y += src.getMovementOnAxis(GameInputSource.Axis.Y_AXIS);
            if(movement.len() > 1.0)
            {
                movement = movement.nor();
            }
            if(src.isThrowButtonPressed())
            {
                if(holdingGoat)
                {
                    this.gameObject.send(new Message("throwGoat"));
                    GoatonWorld.sendGlobalMessage(new Message("throwGoatSound"));
                    holdingGoat = false;
                }
                else
                {
                    //GoatonWorld.sendGlobalMessage(new Message("throw"));
                    this.gameObject.send(new Message("pickup"));
                    GoatonWorld.sendGlobalMessage(new Message("pickupSound"));
                }
            }
            if(src.isDebugButtonPressed())
            {
                GoatonWorld.sendGlobalMessage(new Message("spawnBoss"));
            }
            if(src.isGlowButtonPressed())
            {
                this.gameObject.send(new Message("glow"));
            }
            if (src.isSpinLeftButtonPressed()) {
                if (holdingGoat) {
                    this.gameObject.send(new Message("spinCounterwise"));
                }
            }
            if (src.isSpinRightButtonPressed()) {
                if (holdingGoat) {
                    this.gameObject.send(new Message("spinClockwise"));
                }
            }

            movement.scl(speed);

            gameObject.getBody().setLinearVelocity(movement.x, movement.y);

            gameObject.direction = parseDirection(movement);
        }
    }

    // Player's direction is which way they are facing. Therefore, player can't have a NONE direction after moving
    protected GameObject.Direction parseDirection(Vector2 movement) {
        GameObject.Direction direction;

        // Check if horizontal/vertical then positive or negative
        if (Math.abs(movement.x) > Math.abs(movement.y)) {
            if (movement.x > 0.0) {
                direction = GameObject.Direction.RIGHT;
            } else {
                direction = GameObject.Direction.LEFT;
            }
        } else if (Math.abs(movement.y) > Math.abs(movement.x)) {
            if (movement.y > 0.0) {
                direction = GameObject.Direction.UP;
            } else {
                direction = GameObject.Direction.DOWN;
            }
        } else {
            direction = this.gameObject.direction;
        }

        return direction;
    }

    @Override
    public String getID() {
        return "PlayerMovementComponent";
    }

}
