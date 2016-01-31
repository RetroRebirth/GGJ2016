package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class DemonBossFactory {
    public static GameObject Create(Vector2 lArm, Vector2 rArm)
    {
        GameObject demonBoss = new GameObject();
        demonBoss.addComponent(new DemonBossAnimatorComponent());
        demonBoss.addComponent(new PitPhysicsComponent());
        demonBoss.addComponent(new EnemyComponent(1));
        demonBoss.addComponent((new BossArmComponent(new Sprite(new Texture(Gdx.files.internal(Assets.arm))),
                                                        lArm,
                                                        false)));
        demonBoss.addComponent((new BossArmComponent(new Sprite(new Texture(Gdx.files.internal(Assets.arm))),
                rArm,
                true)));
        ((EnemyComponent)demonBoss.getComponent("EnemyComponent")).canBeDamaged = false;

        demonBoss.layer = Assets.ACTOR_LAYER;
        return demonBoss;
    }


}
