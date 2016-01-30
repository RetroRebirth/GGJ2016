package com.teamname.goaton; /**
 * Created by pya on 1/30/16.
 */

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import java.util.ArrayList;

/* Components needed:
    Input
    Collision?
 */
public class Player extends GameObject {

    /* All goats held */
    private ArrayList<Goat> goatStack;
    private int goatCapacity = 1;

    public Player() {
        /*
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.position);
        this.body = GoatonWorld.world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(8f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.PLAYER;
        fixtureDef.filter.maskBits = ObjectTypes.PLAYER | ObjectTypes.BOUNDARY | ObjectTypes.PIT | ObjectTypes.DEMON;
        fixtureDef.restitution = 0;

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();*/
    }
    public void Pickup() {
        /* Check if stack is not at capacity */
            /* If so check for pickupable goat */
            /* Pick up goat */
    }
    public void Throw() {
        /* Check if stack has a goat at all */
            /* If so throw goat */
    }

    public void Move() {
        /* Move based on input */
    }
}
