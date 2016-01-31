package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.ObjectTypes;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon on 1/30/2016.
 */
public class DemonAuraComponent extends Component {

    private List<GameObject> victimGoats = new LinkedList<GameObject>();

    @Override
    protected void create() {
        CircleShape circle = new CircleShape();
        circle.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.GOAT_DETECTOR;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT;
        fixtureDef.restitution = 0;
        fixtureDef.isSensor = true;

        this.gameObject.getBody().createFixture(fixtureDef);

    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        Fixture f1 = collision.getFixtureA();
        Fixture f2 = collision.getFixtureB();
        if (f1.isSensor() || f2.isSensor()) {
            victimGoats.add(other);
        }
    }
    @Override
    protected void onCollisionExit(Contact collision, GameObject other) {
        Fixture f1 = collision.getFixtureA();
        Fixture f2 = collision.getFixtureB();
        if (f1.isSensor() || f2.isSensor()) {
            victimGoats.remove(other);
        }
    }
    @Override
    protected void update(float dt) {
        for (GameObject o : victimGoats) {
            o.send(new Message("flee", this.gameObject.getBody().getPosition()));
        }
    }

    @Override
    public Component cloneComponent() {
        return new DemonAuraComponent();
    }

    @Override
    public String getID() {
        return "DemonAuraComponent";
    }


}
