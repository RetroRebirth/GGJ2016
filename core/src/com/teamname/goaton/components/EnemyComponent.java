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
    public boolean canBeDamaged = true;
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
           if((gameObject.getComponent("DemonPhysicsComponent")) != null){
               ((DemonPhysicsComponent)(gameObject.getComponent("DemonPhysicsComponent"))).setMaskBits((short)0);

           }
       }
    }

    @Override
    protected void onCollisionEnter(Contact collision, GameObject other) {
        if (collision.getFixtureA().isSensor() || collision.getFixtureB().isSensor()) {
            return;
        }
        if(other.tags.contains("goat") && canBeDamaged)
        {
            if(gameObject.tags.contains("demonboss") &&
                    ((DemonBossPhysicsComponent)gameObject.getComponent("DemonBossPhysicsComponent")).isHitboxOn())
            {
                //don't damage demon, push that goat back
            }
            else {

                health -= 1;
                gameObject.send(new Message("damaged"));
                other.send(new Message("destroy"));
                GoatonWorld.numGoats--;
                GoatonWorld.Destroy(other);
            }
        }
        if (other.tags.contains("player")) {
            // Damage player
//            other.getBody().applyForce(100f, 100f, 0f, 0f, false);
            if (!((PlayerMovementComponent) other.getComponent("PlayerMovementComponent")).hit) {
                GoatonWorld.sendGlobalMessage(new Message("player_hit"));
            }
            ((PlayerMovementComponent) other.getComponent("PlayerMovementComponent")).hit = true;
            if(gameObject.tags.contains("demonboss"))
            {
                other.getBody().setLinearVelocity(new Vector2(0,-300.0f));
                other.getBody().applyLinearImpulse(new Vector2(0, -20), new Vector2(0,-400.0f), false);
            }
            else
            {
                Vector2 dir = other.getPosition().sub(this.gameObject.getPosition()).nor().scl(10.0f);
                other.getBody().setLinearVelocity(dir);
            }
        }
        if(health <= 0 && canBeDamaged)
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
