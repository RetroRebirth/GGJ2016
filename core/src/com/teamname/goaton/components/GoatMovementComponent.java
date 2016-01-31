package com.teamname.goaton.components;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.math.Vector2;
//import com.sun.javafx.scene.traversal.Direction;
import com.teamname.goaton.*;
import com.teamname.goaton.TweenWrappers.LinearVelocityAccessor;

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
    private static final int SPIN_CLOCKWISE = 3;
    private static final int SPIN_COUNTERWISE = 4;

    protected Vector2 fleePoint;

    int state = SPIN_COUNTERWISE;
    int prevState = SPIN_COUNTERWISE;
    private int spinCounter = 3;

    @Override
    protected void create() {

        moveTimer = GoatonWorld.Random.nextFloat() * maxMoveTime;
        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                prevState = state;
                state = HELD;
                gameObject.getBody().setLinearVelocity(0,0);
                gameObject.layer = Assets.THROW_LAYER;
            }
        });

        this.on("onGround", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                prevState = state;
                state = IDLE;
                gameObject.layer = Assets.ACTOR_LAYER;
            }
        });

        this.on("flee", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if (state == IDLE) {
                    prevState = state;
                    state = FLEE;
                    fleePoint = (Vector2) msg.getArg();
                    moveTimer = 2.0f;
                    gameObject.getBody().setLinearVelocity(0, 0);
                }
            }
        });
        this.on("throwGoat", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                prevState = state;
                spinCounter = 3;
                if (prevState == SPIN_CLOCKWISE) {

                } else if (prevState == SPIN_COUNTERWISE) {

                }
            }
        });

        this.on("throw", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                prevState = state;
                Vector2 curVel = gameObject.getBody().getLinearVelocity();

                /*Tween.to(gameObject.getBody(), LinearVelocityAccessor.TWEEN_XY, 0.75f)
                        .target(-curVel.y, curVel.x)
                        .start(GoatonWorld.TweenManager);*/


                spinCounter = 3;
                if (prevState == SPIN_CLOCKWISE) {
                    Tween.to(gameObject.getBody(), LinearVelocityAccessor.TWEEN_XY, 0.75f)
                            .target(curVel.y, -curVel.x)
                            //.target(curVel.y)
                            .start(GoatonWorld.TweenManager);
                } else if (prevState == SPIN_COUNTERWISE) {
                    Tween.to(gameObject.getBody(), LinearVelocityAccessor.TWEEN_XY, 0.75f)
                            .target(-curVel.y, curVel.x)
                            //.target(-curVel.y)
                            .start(GoatonWorld.TweenManager);
                }
            }
        });

        this.on("spinClockwise", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                spinCounter = 3;
                state = SPIN_CLOCKWISE;
            }
        });
        this.on("spinCounterwise", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                spinCounter = 0;
                state = SPIN_COUNTERWISE;
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



            case SPIN_CLOCKWISE:
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
            case SPIN_COUNTERWISE:
                spinTime -= dt;
                if (spinTime < 0) {
                    if (spinCounter > 3) {
                        spinCounter = 0;
                    }
                    gameObject.direction = GameObject.Direction.values()[spinCounter];
                    spinCounter++;
                    spinTime = maxSpinTime;
                }

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
        /*gameObject.getBody().applyForceToCenter(new Vector2(mov.x * 10, mov.y * 10), true);
        gameObject.getBody().applyForceToCenter(new Vector2(-mov.y * 10, mov.x * 10), true);*/
    }
    @Override
    public String getID() {
        return "GoatMovementComponent";
    }
}
