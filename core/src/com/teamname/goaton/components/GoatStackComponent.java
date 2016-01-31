package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;

/**
 * Created by kpidding on 1/30/16.
 */
public class GoatStackComponent extends Component {
    private GameObject cGoat;

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
                    cGoat.send(new Message("throw"));
                    cGoat = null;
                }

            }
        });

    }

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
