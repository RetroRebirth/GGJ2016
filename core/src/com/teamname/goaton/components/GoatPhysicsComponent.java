package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.*;

/**
 * Created by Simon on 1/30/2016.
 */
public class GoatPhysicsComponent extends Component {

    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        CircleShape circle = new CircleShape();
        circle.setRadius(8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.GOAT;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT |
                                    ObjectTypes.BOUNDARY |
                                    ObjectTypes.PIT |
                                    ObjectTypes.DEMON |
                                    ObjectTypes.PICKUP_DETECTOR;
        fixtureDef.restitution = 0;

        Fixture fixture = this.gameObject.getBody().createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public Component cloneComponent() {
        return new GoatPhysicsComponent();
    }

    @Override
    public String getID() {
        return "GoatPhysicsComponent";
    }
}
