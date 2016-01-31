package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.teamname.goaton.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class EnemyComponent extends Component {

    public int health;
    public boolean canBeDamaged;
    public int initialHealth;
    public  EnemyComponent(int health)
    {
        this.health = health;
        this.initialHealth = health;
    }

    @Override
    protected void update(float dt) {
       if(health <= 0)
       {
           ((DemonPhysicsComponent)(gameObject.getComponent("DemonPhysicsComponent"))).setMaskBits((short)0);
       }
    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        if (collision.getFixtureA().isSensor() || collision.getFixtureB().isSensor()) {
            return;
        }
        if(other.tags.contains("goat"))
        {
            health-=1;
            gameObject.send(new Message("damaged"));
            other.send(new Message("destroy"));
            GoatonWorld.numGoats--;
            GoatonWorld.Destroy(other);

        }
        if (other.tags.contains("player")) {
            // Damage player
//            other.getBody().applyForce(100f, 100f, 0f, 0f, false);
            if (!((PlayerMovementComponent) other.getComponent("PlayerMovementComponent")).hit) {
                GoatonWorld.sendGlobalMessage(new Message("player_hit"));
            }
            ((PlayerMovementComponent) other.getComponent("PlayerMovementComponent")).hit = true;
            Vector2 dir = other.getPosition().sub(this.gameObject.getPosition()).nor().scl(10f);
            other.getBody().setLinearVelocity(dir);
        }
        if(health <= 0)
        {
            gameObject.send(new Message("destroy"));
            if (gameObject.tags.contains("demon")) {
                GoatonWorld.numDemons--;
                ((AnimatedSpriteRenderComponent)(gameObject.getComponent("DemonAnimatedSpriteComponent"))).setVisible(false);
               //demon is destroyed when particle effect is finished; check ParticleCompnoent
            }
        }
    }

    public int getHealth() {
        return health;
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
