package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;

/**
 * Created by pya on 1/31/16.
 */
public class PitCollisionComponent extends Component {
    int goatCapacity;

    @Override
    protected void create() {


    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        System.out.println("Goat is in pit");
        Fixture f1 = collision.getFixtureA();
        Fixture f2 = collision.getFixtureB();
        Body otherBody = other.getBody();

        other.

        if (f1.getBody() == otherBody) {

        } else if (f2.getBody() == otherBody) {

        }


    }

    /*@Override
    protected void onCollisionExit(Contact collision, GameObject other) {
        System.out.println("Goat is out of pit");
    }*/

    @Override
    public Component cloneComponent() {
        return new PitCollisionComponent();
    }

    @Override
    public String getID() {
        return "PitCollisionComponent";
    }
}
