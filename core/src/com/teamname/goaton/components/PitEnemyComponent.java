package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.MsgHandler;
import com.teamname.goaton.Prefabs.PitDyingGoatFactory;

/**
 * Created by pya on 1/31/16.
 */
public class PitEnemyComponent extends EnemyComponent {


    public PitEnemyComponent(int health) {
        super(health);
    }

    @Override
    protected void create() {

    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        super.onCollisionEnter(collision, other);
        if(health <= 0)
        {
            GameObject go = GameObject.Instantiate(PitDyingGoatFactory.Create(other));
            if(go.components.containsKey("ParticleComponent"))
            {
                ((ParticleComponent)(go.getComponent("ParticleComponent"))).setRunEffect(true);
            }
            go.setPosition(gameObject.getPosition());
        }
    }
    @Override
    public Component cloneComponent() {
        return new PitEnemyComponent(1);
    }

    @Override
    public String getID() {
        return "PitEnemyComponent";
    }
}
