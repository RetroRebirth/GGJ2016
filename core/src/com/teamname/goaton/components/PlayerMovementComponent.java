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
    private boolean holdingGoat = false;
    @Override
    protected void create() {
        this.on("pickupGoat",new MsgHandler() {
                @Override
                public void handle(Message msg) {
                    holdingGoat = true;
                }
        });

    }

    @Override
    public Component cloneComponent() {
        return new PlayerMovementComponent(src);
    }

    @Override
    protected void update(float dt) {
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
                holdingGoat = false;
            }
            else
            {
                //GoatonWorld.sendGlobalMessage(new Message("throw"));
                this.gameObject.send(new Message("pickup"));

            }
        }
        movement.scl(speed);
        gameObject.getBody().setLinearVelocity(movement.x, movement.y);

        gameObject.direction = parseDirection(movement);
    }

    protected GameObject.Direction parseDirection(Vector2 movement) {
        GameObject.Direction direction = GameObject.Direction.NONE;

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
        }

        return direction;
    }

    @Override
    public String getID() {
        return "PlayerMovementComponent";
    }

}
