package com.teamname.goaton.components;

import com.badlogic.gdx.physics.box2d.Contact;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;

/**
 * Created by kpidding on 1/30/16.
 */
public class EnemyComponent extends Component {
    public int health;
    public int initialHealth;
    public  EnemyComponent(int health)
    {
        this.health = health;
        this.initialHealth = health;
    }
    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        if (collision.getFixtureA().isSensor() || collision.getFixtureB().isSensor()) {
            return;
        }
        if(other.tags.contains("goat"))
        {
            health-=1;
            GoatonWorld.Destory(other);
        }
        if(health <= 0)
        {

            GoatonWorld.Destory(this.gameObject);
        }
    }

    @Override
    public String getID() {
        return "EnemyComponent";
    }

    @Override
    public Component cloneComponent() {
        return new EnemyComponent(initialHealth);
    }
}
