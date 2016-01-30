package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.World;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatMovementComponent extends Component {
    enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }
    private Direction direction = Direction.NONE;
    private float moveTimer;
    private float maxMoveTime = 3.f;
    private float minMoveTime = 0.5f;
    private float moveSpeed = 55f;

    @Override
    protected void create() {
        moveTimer = World.Random.nextFloat() * maxMoveTime;
    }

    @Override
    protected void update(float dt) {
        super.update(dt);
        moveTimer -= dt;
        if(moveTimer < 0)
        {
            //Higher weight to stop moving.
            if(direction != Direction.NONE & World.Random.nextFloat() > 0.5)
            {
                direction = Direction.NONE;
            }
            {

                direction = World.RandomEnum(Direction.class);
            }
            moveTimer = World.Random.nextFloat() * (maxMoveTime-minMoveTime) + minMoveTime;
        }
        switch (direction)
        {
            case UP:
                gameObject.position.y += moveSpeed * dt;
                break;
            case DOWN:
                gameObject.position.y -= moveSpeed * dt;
                break;
            case LEFT:
                gameObject.position.x -= moveSpeed * dt;
                break;
            case RIGHT:
                gameObject.position.x += moveSpeed*dt;
                break;
            case NONE:
                break;
            default:
                break;
        }

    }

    @Override
    public String getID() {
        return "GoatMovementComponent";
    }
}
