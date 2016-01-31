package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.ObjectTypes;

/**
 * Created by pya on 1/30/16.
 */
public class PitPhysicsComponent extends Component {

    @Override
    protected void create() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        CircleShape circle = new CircleShape();
        circle.setRadius(8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.PIT;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT | ObjectTypes.BOUNDARY | ObjectTypes.DEMON;
        fixtureDef.restitution = 0;

        Fixture fixture = this.gameObject.getBody().createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public Component cloneComponent() {
        return new PitPhysicsComponent();
    }

    @Override
    public String getID() {return "PitPhysicsComponent";}
}
