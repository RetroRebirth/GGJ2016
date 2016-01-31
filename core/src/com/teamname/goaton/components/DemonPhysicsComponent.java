package com.teamname.goaton.components;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.*;

/**
 * Created by Simon on 1/30/2016.
 */
public class DemonPhysicsComponent extends Component {
    Fixture gFix;
    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        CircleShape circle = new CircleShape();
        circle.setRadius(0.7f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.DEMON;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT_AIR |
                                    ObjectTypes.BOUNDARY |
                                    ObjectTypes.PIT |
                                    ObjectTypes.DEMON |
                                    ObjectTypes.PLAYER;
        fixtureDef.restitution = 0.5f;

        gFix = this.gameObject.getBody().createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public Component cloneComponent() {
        return new DemonPhysicsComponent();
    }

    @Override
    public String getID() {
        return "DemonPhysicsComponent";
    }
}
