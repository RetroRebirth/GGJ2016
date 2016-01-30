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
                cGoat.setPosition(new Vector2(0,35));
            }
        });

        this.on("throwGoat", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                if(cGoat != null)
                {
                    cGoat.setPosition(gameObject.getPosition().add(new Vector2(0,35)));
                    gameObject.removeChild(cGoat);
                    cGoat.getBody().setLinearVelocity(gameObject.getBody().getLinearVelocity().scl(1.5f));
                    cGoat.send(new Message("throw"));
                    cGoat = null;
                }

            }
        });

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
