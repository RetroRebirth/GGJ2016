package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.ObjectTypes;

/**
 * Created by Simon on 1/30/2016.
 */
public class BoundsPhysicsComponent extends Component {
    Fixture gFix;
    Vector2[] boundVertices;
    boolean isLoop;

    public BoundsPhysicsComponent(Vector2[] vertices, boolean isLoop)
    {
        boundVertices = vertices;
        this.isLoop = isLoop;
    }

    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        ChainShape boundaries = new ChainShape();

        boundaries.createLoop(boundVertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boundaries;
        fixtureDef.filter.categoryBits = ObjectTypes.BOUNDARY;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT |
                                    ObjectTypes.PIT |
                                    ObjectTypes.DEMON |
                                    ObjectTypes.PLAYER |
                                    ObjectTypes.GOAT_AIR;
        fixtureDef.restitution = 0;

        gFix = this.gameObject.getBody().createFixture(fixtureDef);

        boundaries.dispose();
    }

    @Override
    public Component cloneComponent() {
        return new BoundsPhysicsComponent(boundVertices, isLoop);
    }

    @Override
    public String getID() {
        return "BoundsPhysicsComponent";
    }
}
