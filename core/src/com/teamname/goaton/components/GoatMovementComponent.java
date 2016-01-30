package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

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
    private float moveSpeed = 50f;
    boolean update = true;
    @Override
    protected void create() {

        moveTimer = GoatonWorld.Random.nextFloat() * maxMoveTime;
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                update = false;
                gameObject.getBody().setLinearVelocity(0,0);
            }
        });
        this.on("onGround", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                update = true;
            }
        });
    }

    @Override
    public Component cloneComponent() {
        return new GoatMovementComponent();
    }

    @Override
    protected void update(float dt) {
        super.update(dt);
        if(update) {
            moveTimer -= dt;
            if (moveTimer < 0) {
                //Higher weight to stop moving.
                if (direction != Direction.NONE & GoatonWorld.Random.nextFloat() > 0.5) {
                    direction = Direction.NONE;
                }
                {

                    direction = GoatonWorld.RandomEnum(Direction.class);
                }
                moveTimer = GoatonWorld.Random.nextFloat() * (maxMoveTime - minMoveTime) + minMoveTime;
            }
            Vector2 mov = new Vector2();
            switch (direction) {
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
        /*
        if(mov.len() == 0)
        {
            Body goBody = gameObject.getBody();
            Vector2 vel = new Vector2(goBody.getLinearVelocity()).scl(-0.8f);
            gameObject.getBody().applyLinearImpulse(vel.x,vel.y,gameObject.position.x,gameObject.position.y,true);
        }
        */

            gameObject.getBody().setLinearVelocity(mov.x, mov.y);//;applyLinearImpulse(mov.x,mov.y,gameObject.position.x, gameObject.position.y,true);
        }

    }

    @Override
    public String getID() {
        return "GoatMovementComponent";
    }
}
