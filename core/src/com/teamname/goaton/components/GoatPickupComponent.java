package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.*;

/**
 * Created by Simon on 1/30/2016.
 */
public class GoatPickupComponent extends Component {

    protected void create() {
        PolygonShape arc = new PolygonShape();
        Vector2 verticies[] = new Vector2[8];
        float radius = 12f;
        verticies[0] = new Vector2(0f, 0f);
        for (int i = 0; i < 7; i++) {
            float angle = i / 6.0f * (float)Math.toRadians(90.);
            verticies[i + 1] = new Vector2((float)Math.cos(angle) * radius, (float)Math.sin(angle) * radius);
        }
        arc.set(verticies);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = arc;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = ObjectTypes.PICKUP_DETECTOR;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT;

        Fixture fixture = this.gameObject.getBody().createFixture(fixtureDef);

        arc.dispose();
        final GoatPickupComponent thisCmp = this;
        on("pickup",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                //get list of currently colliding goats, get closest, pick it up (add to child, send message to goat)
            }
        });
    }
    @Override
    public String getID() {
        return "GoatPickupComponent";
    }
}
