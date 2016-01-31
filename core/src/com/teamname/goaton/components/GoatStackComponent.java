package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.Prefabs.GoatFactory;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatStackComponent extends Component {
    private GameObject cGoat;
    private Vector2 straight;
    private Vector2 curve;
    //private float throwTime = 0;

    @Override
    protected void create() {
        this.on("pickupGoat", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                cGoat = (GameObject)msg.getArg();
                cGoat.send(new Message("pickup"));
                gameObject.addChild(cGoat);
                cGoat.setPosition(new Vector2(0,1f));
            }
        });

        this.on("throwGoat", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if(cGoat != null)
                {
                    cGoat.setPosition(gameObject.getPosition().add(new Vector2(0,1)));
                    gameObject.removeChild(cGoat);
                    cGoat.getBody().setLinearVelocity(calcGoatThrowVelocity());
                    /*Vector2 mov = calcGoatThrowVelocity();
                    straight = (new Vector2(mov.x * 50, mov.y * 50));
                    curve = (new Vector2(-mov.y * 20, mov.x * 20));
                    throwTime = GoatFactory.THROWTIME;*/
                    //cGoat.getBody().applyForceToCenter(new Vector2(mov.x * 50, mov.y * 50), true);
                    //cGoat.getBody().applyForceToCenter(new Vector2(-mov.y * 20, mov.x * 20), true);
                    //cGoat.getBody())
                    cGoat.send(new Message("throw"));
                    cGoat = null;
                }

            }
        });


    }

/*    @Override
    protected void update(float dt) {
        if (throwTime > 0 && cGoat != null) {
            cGoat.getBody().applyForceToCenter(straight, true);
            cGoat.getBody().applyForceToCenter(curve, true);
            throwTime--;
        } else {
            cGoat = null;
        }
    }*/

    protected Vector2 calcGoatThrowVelocity() {
        // Get the player's speed magnitude
        float mag = ((PlayerMovementComponent) this.gameObject.getComponent("PlayerMovementComponent")).speed;
        Vector2 vec = new Vector2(0.0f, 0.0f);

        switch (this.gameObject.direction) {
            case UP:
                vec.y = mag;
                break;
            case DOWN:
                vec.y = -mag;
                break;
            case LEFT:
                vec.x = -mag;
                break;
            case RIGHT:
                vec.x = mag;
                break;
        }
        // Scale how far the goat flies
        vec = vec.scl(1.15f);

        return vec;
    }

    @Override
    public String getID() {
        return "GoatStackComponent";
    }

    @Override
    public Component cloneComponent() {
        return null;
    }
}
