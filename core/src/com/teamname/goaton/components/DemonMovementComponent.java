package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.*;

//import com.sun.javafx.scene.traversal.Direction;

/**
 * Created by Chris Williams on 1/31/16.
 */
public class DemonMovementComponent extends Component {

    private float moveTimer;
    private float maxMoveTime = 3.0f;
    private float minMoveTime = 0.5f;
    private float moveSpeed = 5f;

    float distToPlayer;
    Vector2 playerTarget;
    float chargeTimer = 0f;

    private static final int ROAM = 0;
    private static final int CHASE = 1;
    private static final int WINDUP = 2;
    private static final int CHARGE = 3;
    private static final int IDLE = 4;

    private int state = ROAM;

    @Override
    protected void create() {
        this.on("hit", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if (state == CHARGE) {
                    state = IDLE;
                    gameObject.direction = GameObject.Direction.NONE;
                }
            }
        });
//        this.on("destroy", new MsgHandler() {
//            @Override
//            public void handle(Message msg) {
//                GoatonWorld.Destroy(gameObject);
//            }
//        });
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
            // Choose to move in a random direction, stop moving, or chase player
            if(state == ROAM || state == IDLE) {
                float rand = GoatonWorld.Random.nextFloat();
                if (rand > 0.8) {
                    // Chill out and look cool by standing still
                    gameObject.direction = GameObject.Direction.NONE;
                    state = IDLE;
                } else {
                    // Take a stroll on memory lane, it's your time to enjoy the scenery
                    gameObject.direction = GoatonWorld.RandomEnum(GameObject.Direction.class);
                    state = ROAM;
                }
            }
            Vector2 toPlayerVector = new Vector2(this.gameObject.getPosition().x - Scene.Player.getPosition().x,
                    this.gameObject.getPosition().y-Scene.Player.getPosition().y);
            distToPlayer = toPlayerVector.len();
            if (distToPlayer < 10f) {
                if (GoatonWorld.Random.nextFloat() < 0.6f) {
                    state = CHASE;
                }
                else if (state == CHASE || state == IDLE || state == ROAM){
                    state = WINDUP;
                    playerTarget = new Vector2(Scene.Player.getPosition().sub(this.gameObject.getPosition()).nor());
                    gameObject.direction = parseDirection(playerTarget);
                    chargeTimer = 0.4f;
                    this.gameObject.send(new Message("windup"));
                }
            }
            if (state == WINDUP && chargeTimer <= 0f) {
                this.gameObject.send(new Message("charge"));
                state = CHARGE;
            }
            moveTimer = GoatonWorld.Random.nextFloat() * (maxMoveTime - minMoveTime) + minMoveTime;
            //System.out.println(moveTimer);
        }

        chargeTimer -= dt;
        this.move();
    }

    private void move() {
        switch (state) {
            case IDLE:
            case ROAM:
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

                this.gameObject.getBody().setLinearVelocity(mov);
                break;
            case CHASE:
                Vector2 dir = Scene.Player.getPosition().sub(this.gameObject.getPosition()).nor().scl(1f);
                this.gameObject.direction = parseDirection(dir);
                this.gameObject.getBody().setLinearVelocity(dir.scl(moveSpeed * 1.5f));
                break;
            case CHARGE:
                Vector2 movement = new Vector2(playerTarget);
                this.gameObject.direction = parseDirection(playerTarget);
                this.gameObject.getBody().setLinearVelocity(movement.scl(6.0f * moveSpeed)
                        .add(Scene.Player.getPosition().sub(this.gameObject.getPosition()).nor().scl(2 * moveSpeed)));
                break;
            case WINDUP:
                this.gameObject.getBody().setLinearVelocity(this.gameObject.getBody().getLinearVelocity().scl(0.8f));
                break;
        }
    }

    // Figure out which way the demon is facing when chasing the player
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
        return "DemonMovementComponent";
    }
}
