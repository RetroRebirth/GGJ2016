package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.teamname.goaton.*;

/**
 * Created by Simon on 1/30/2016.
 */
public class GoatPhysicsComponent extends Component {
    Fixture gFix;
    @Override
    protected void create() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.gameObject.getPosition());
        this.gameObject.addPhysicsBody(GoatonWorld.world.createBody(bodyDef));

        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.GOAT;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT |
                                    ObjectTypes.BOUNDARY |
                                    ObjectTypes.PIT |
                                    ObjectTypes.DEMON |
                                    ObjectTypes.PICKUP_DETECTOR;
        fixtureDef.restitution = 0;

        gFix = this.gameObject.getBody().createFixture(fixtureDef);

        circle.dispose();

        this.on("pickup", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                Filter heldFilter = gFix.getFilterData();
                heldFilter.maskBits = ObjectTypes.DEMON | ObjectTypes.PIT;
                gFix.setFilterData(heldFilter);
            }
        });
        this.on("onGround", new MsgHandler() {
            @Override
            public void handle(Message msg) {
                Filter groundFilter = gFix.getFilterData();
                groundFilter.maskBits =
                        ObjectTypes.GOAT |
                        ObjectTypes.BOUNDARY |
                        ObjectTypes.PIT |
                        ObjectTypes.DEMON |
                        ObjectTypes.PICKUP_DETECTOR;
                gFix.setFilterData(groundFilter);
            }
        });
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
