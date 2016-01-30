package com.teamname.goaton.components;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon on 1/30/2016.
 */
public class PlayerPickupComponent extends Component {

    List<GameObject> legalGoats = new LinkedList<GameObject>();
    protected void create() {
        PolygonShape arc = new PolygonShape();
        Vector2 verticies[] = new Vector2[8];
        float radius = 50f;
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
        final PlayerPickupComponent thisCmp = this;
        on("pickup",new MsgHandler() {
            @Override
            public void handle(Message msg) {
                float minDist = 1000f;
                GameObject closestGoat = null;
                for (GameObject goat : legalGoats) {
                    if (thisCmp.gameObject.getBody().getPosition().dst2(goat.getBody().getPosition()) < minDist) {
                        closestGoat = goat;
                        minDist = thisCmp.gameObject.getBody().getPosition().dst2(goat.getBody().getPosition());
                    }
                }
                System.out.println("Attempt to pickup");
                if (closestGoat != null) {
                    gameObject.send(new Message("pickupGoat",closestGoat));
                }
            }
        });
    }


    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        System.out.println("Got a goat");
        Fixture f1 = collision.getFixtureA();
        Fixture f2 = collision.getFixtureB();
        if (f1.isSensor() || f2.isSensor()) {
            //other.send(new Message("pickup"));
            legalGoats.add(other);
        }
    }
    protected void onCollisionExit(Contact collision, GameObject other) {
        System.out.println("Lost a goat");
        Fixture f1 = collision.getFixtureA();
        Fixture f2 = collision.getFixtureB();
        if (f1.isSensor() || f2.isSensor()) {
            legalGoats.remove(other);
        }
    }

    @Override
    public String getID() {
        return "PlayerPickupComponent";
    }

    @Override
    public Component cloneComponent() {
        return new PlayerPhysicsComponent();

    }
}
