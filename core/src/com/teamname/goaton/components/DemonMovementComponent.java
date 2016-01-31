package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.*;

//import com.sun.javafx.scene.traversal.Direction;

/**
 * Created by Chris Williams on 1/31/16.
 */
public class DemonMovementComponent extends Component {

    private float moveTimer;
    private float maxMoveTime = 3.f;
    private float minMoveTime = 0.5f;
    private float moveSpeed = 2.5f;

    @Override
    protected void create() {
        super.create();
    }

    @Override
    public Component cloneComponent() {
        return new DemonMovementComponent();
    }

    @Override
    protected void update(float dt) {
        super.update(dt);

        moveTimer -= dt;
        if (moveTimer < 0) {
            //Higher weight to stop moving.
            if (gameObject.direction != GameObject.Direction.NONE & GoatonWorld.Random.nextFloat() > 0.5) {
                gameObject.direction = GameObject.Direction.NONE;
            } else {
                gameObject.direction = GoatonWorld.RandomEnum(GameObject.Direction.class);
            }
            moveTimer = GoatonWorld.Random.nextFloat() * (maxMoveTime - minMoveTime) + minMoveTime;
        }
        this.move();
    }

    private void move() {
        Vector2 mov = new Vector2();
        switch (gameObject.direction) {
            case UP:
                mov.y += moveSpeed;
                break;
            case DOWN:
                mov.y -= moveSpeed;
                break;
            case LEFT:
                mov.x -= moveSpeed;
                break;
            case RIGHT:
                mov.x += moveSpeed;
                break;
            case NONE:

                break;
            default:
                break;
        }
        gameObject.getBody().setLinearVelocity(mov.x, mov.y);
        /*gameObject.getBody().applyForceToCenter(new Vector2(mov.x * 10, mov.y * 10), true);
        gameObject.getBody().applyForceToCenter(new Vector2(-mov.y * 10, mov.x * 10), true);*/
    }
    @Override
    public String getID() {
        return "DemonMovementComponent";
    }
}
