package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.ObjectTypes;

/**
 * Created by Simon on 1/30/2016.
 */
public class BarrierPhysicsComponent extends Component {
    Fixture gFix;
    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(8f, 8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.filter.categoryBits = ObjectTypes.BOUNDARY;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT |
                                    ObjectTypes.PIT |
                                    ObjectTypes.DEMON |
                                    ObjectTypes.PLAYER;
        fixtureDef.restitution = 0;

        gFix = this.gameObject.getBody().createFixture(fixtureDef);

        rect.dispose();
    }

    @Override
    public Component cloneComponent() {
        return new BarrierPhysicsComponent();
    }

    @Override
    public String getID() {
        return "BarrierPhysicsComponent";
    }
}
