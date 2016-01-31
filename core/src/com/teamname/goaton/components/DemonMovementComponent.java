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
    private float maxMoveTime = 3.f;
    private float minMoveTime = 0.5f;
    private float moveSpeed = 2.5f;
    private boolean chasingPlayer = false;

    @Override
    protected void create() {
        super.create();
        this.on("destroy", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                GoatonWorld.Destroy(gameObject);
            }
        });
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
            float rand = GoatonWorld.Random.nextFloat();
            if (rand > 0.9) {
                // Chill out and look cool by standing still
                gameObject.direction = GameObject.Direction.NONE;
                chasingPlayer = false;
            } else if (rand > 0.4) {
                // Take a stroll on memory lane, it's your time to enjoy the scenery
                gameObject.direction = GoatonWorld.RandomEnum(GameObject.Direction.class);
                chasingPlayer = false;
            } else {
                // Hey, who's that one guy who's throwing the goats? You should fuck his shit up
                chasingPlayer = true;
            }
            moveTimer = GoatonWorld.Random.nextFloat() * (maxMoveTime - minMoveTime) + minMoveTime;
        }
        this.move();
    }

    private void move() {
        if (!chasingPlayer) {
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
        } else {
            Vector2 dir = Scene.Player.getPosition().sub(this.gameObject.getPosition()).nor().scl(1f);
            this.gameObject.direction = parseDirection(dir);
            this.gameObject.getBody().setLinearVelocity(dir);
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
