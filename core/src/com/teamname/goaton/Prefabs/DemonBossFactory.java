package com.teamname.goaton.Prefabs;

import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.DemonBossAnimatorComponent;
import com.teamname.goaton.components.DemonPhysicsComponent;
import com.teamname.goaton.components.EnemyComponent;
import com.teamname.goaton.components.PitPhysicsComponent;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonBossFactory {
    public static GameObject Create()
    {
        GameObject demonBoss = new GameObject();
        demonBoss.addComponent(new DemonBossAnimatorComponent());
        demonBoss.addComponent(new PitPhysicsComponent());
        demonBoss.addComponent(new EnemyComponent(20));
        demonBoss.layer = Assets.ACTOR_LAYER;
        return demonBoss;
    }

}
