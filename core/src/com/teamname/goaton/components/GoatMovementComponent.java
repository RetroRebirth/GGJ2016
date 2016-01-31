package com.teamname.goaton.components;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.math.Vector2;
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
    private float spinTime = 0.05f;
    private float maxSpinTime = 0.05f;
    private float minSpinTime = 0.05f;
    private float moveSpeed = 2.5f;
    private int spinCounter = 3;
    boolean wander = true;
    boolean spin = false;


    @Override
    protected void create() {

        moveTimer = GoatonWorld.Random.nextFloat() * maxMoveTime;
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                wander = false;
                gameObject.getBody().setLinearVelocity(0,0);
            }
        });
        this.on("onGround", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                wander = true;
            }
        });
        this.on("suckIntoHole", new MsgHandler() {
            @Override
            public void handle(Message msg) {
            wander = false;
            spin = true;

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
        if(wander) {
            moveTimer -= dt;
            if (moveTimer < 0) {
                //Higher weight to stop moving.
                if (direction != Direction.NONE & GoatonWorld.Random.nextFloat() > 0.5) {
                    direction = Direction.NONE;
                } else {
                    direction = GoatonWorld.RandomEnum(Direction.class);
                }
                moveTimer = GoatonWorld.Random.nextFloat() * (maxMoveTime - minMoveTime) + minMoveTime;
            }

            this.move();

        /*
        if(mov.len() == 0)
        {
            Body goBody = gameObject.getBody();
            Vector2 vel = new Vector2(goBody.getLinearVelocity()).scl(-0.8f);
            gameObject.getBody().applyLinearImpulse(vel.x,vel.y,gameObject.position.x,gameObject.position.y,true);
        }
        */

            //;applyLinearImpulse(mov.x,mov.y,gameObject.position.x, gameObject.position.y,true);
        } else if (spin){
            spinTime -= dt;
            if (spinTime < 0) {
                if (spinCounter < 0) {
                    spinCounter = 3;
                }
                direction = Direction.values()[spinCounter];
                spinCounter--;
                spinTime = maxSpinTime;
            }
        }

    }

    private void move() {
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
        gameObject.getBody().setLinearVelocity(mov.x, mov.y);
    }
    @Override
    public String getID() {
        return "GoatMovementComponent";
    }

    public Direction getDirection() {
        return direction;
    }
}
