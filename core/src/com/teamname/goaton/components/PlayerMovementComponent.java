package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.Input.GameInputSource;
import com.teamname.goaton.Message;

/**
 * Created by kpidding on 1/30/16.
 */
public class PlayerMovementComponent extends Component {
    private GameInputSource src;
    public float speed = 100.0f;
    public PlayerMovementComponent(GameInputSource src)
    {
        this.src = src;
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
        gameObject.position.add(movement.scl(speed * dt));

        if(src.isThrowButtonPressed())
        {
           gameObject.send(new Message("shoot"));
        }
    }

    @Override
    public String getID() {
        return "PlayerMovementComponent";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new PlayerMovementComponent(src);
    }
}
