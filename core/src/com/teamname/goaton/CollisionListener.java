package com.teamname.goaton;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by kpidding on 1/30/16.
 */
public class CollisionListener implements ContactListener {
    @Override
    public void endContact(Contact contact) {
        GameObject a = (GameObject) contact.getFixtureA().getBody().getUserData();
        GameObject b = (GameObject) contact.getFixtureB().getBody().getUserData();
        if (a != null)
        {
            a.onCollisionExit(contact, b);
        }
        if(b != null)
        {
            b.onCollisionExit(contact, a);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    @Override
    public void beginContact(Contact contact) {
        GameObject a = (GameObject) contact.getFixtureA().getBody().getUserData();
        GameObject b = (GameObject) contact.getFixtureB().getBody().getUserData();
        if (a != null)
        {
            a.onCollisionEnter(contact, b);
        }
        if(b != null)
        {
            b.onCollisionEnter(contact, a);
        }
    }
};