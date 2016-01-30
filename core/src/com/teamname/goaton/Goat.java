package com.teamname.goaton;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Fixture;

import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class Goat extends GameObject {
    public static final float THROWTIME = 0.75f;
    /* com.teamname.goaton.Goat color */
    public Color color;

    public Goat(HashMap<String, Component> comps, Color color) {
        components = new HashMap<String, Component>(comps);

        this.color = color;
        /*
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.position);
        this.body = GoatonWorld.world.createBody(bodyDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(8f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.filter.categoryBits = ObjectTypes.GOAT;
        fixtureDef.filter.maskBits = ObjectTypes.GOAT | ObjectTypes.BOUNDARY | ObjectTypes.PIT | ObjectTypes.DEMON;
        fixtureDef.restitution = 0;

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();*/

    }


    public void update() {

    }

    public void wander() {

    }

    public void runAway() {

    }
}
