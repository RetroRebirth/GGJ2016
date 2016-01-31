package com.teamname.goaton.components;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.math.Vector2;
//import com.sun.javafx.scene.traversal.Direction;
import com.teamname.goaton.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatMovementComponent extends Component {

    private float moveTimer;
    private float maxMoveTime = 3.f;
    private float minMoveTime = 0.5f;
    private float spinTime = 0.05f;
    private float maxSpinTime = 0.05f;
    private float minSpinTime = 0.05f;
    private float moveSpeed = 2.5f;


    private static final int IDLE = 0;
    private static final int FLEE = 1;
    private static final int HELD = 2;
    private static final int SPIN = 3;

    protected Vector2 fleePoint;

    int state = IDLE;
    private int spinCounter = 3;

    @Override
    protected void create() {

        moveTimer = GoatonWorld.Random.nextFloat() * maxMoveTime;
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {

                state = HELD;
                gameObject.getBody().setLinearVelocity(0,0);
            }
        });

        this.on("onGround", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                state = IDLE;
            }
        });

        this.on("flee", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if (state == IDLE) {
                    state = FLEE;
                    fleePoint = (Vector2) msg.getArg();
                    moveTimer = 2.0f;
                    gameObject.getBody().setLinearVelocity(0, 0);
                }
            }
        });
        this.on("suckIntoHole", new MsgHandler() {
            @Override
            public void handle(Message msg) {
            state = SPIN;

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
        switch(state) {
            case IDLE:
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
        /*
        if(mov.len() == 0)
        {
            Body goBody = gameObject.getBody();
            Vector2 vel = new Vector2(goBody.getLinearVelocity()).scl(-0.8f);
            gameObject.getBody().applyLinearImpulse(vel.x,vel.y,gameObject.position.x,gameObject.position.y,true);
        }
        */
                break;
            case FLEE:
                //move in direction
                moveTimer -= dt;
                if (moveTimer < 0f) {
                    state = IDLE;
                }
                Vector2 move = new Vector2(this.gameObject.getPosition().x - this.fleePoint.x, this.gameObject.getPosition().y - this.fleePoint.y);
                move = move.scl(moveSpeed/move.len());
                gameObject.getBody().setLinearVelocity(move.x, move.y);

                break;


            //;applyLinearImpulse(mov.x,mov.y,gameObject.position.x, gameObject.position.y,true);
            case SPIN:
                spinTime -= dt;
                if (spinTime < 0) {
                    if (spinCounter < 0) {
                        spinCounter = 3;
                    }
                    gameObject.direction = GameObject.Direction.values()[spinCounter];
                    spinCounter--;
                    spinTime = maxSpinTime;
                }
                break;
        }

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
    }
    @Override
    public String getID() {
        return "GoatMovementComponent";
    }
}
