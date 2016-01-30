package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.ObjectTypes;

/**
 * Created by Simon on 1/30/2016.
 */
public class PlayerPhysicsComponent extends Component {

    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.position);
        this.gameObject.body = GoatonWorld.world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(8f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.PLAYER;
        fixtureDef.filter.maskBits = ObjectTypes.PLAYER | ObjectTypes.BOUNDARY | ObjectTypes.PIT | ObjectTypes.DEMON;
        fixtureDef.restitution = 0;
        Fixture fixture = this.gameObject.body.createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public String getID() {
        return "PlayerPhysicsComponent";
    }
}
