package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Message;
import com.teamname.goaton.Prefabs.GoatFactory;

/**
 * Created by pya on 1/31/16.
 */
public class GoatSpawnerComponent extends SpawnerComponent {

    public GoatSpawnerComponent() {
        super();
        maxTime = 2f;
        minTime = 0.5f;


    }

    @Override
    public Component cloneComponent() {
        return new GoatSpawnerComponent();
    }
    @Override
    public void update(float dt) {
        timeBetweenSpawn -= dt;
        if (timeBetweenSpawn < 0 && GoatonWorld.numGoats <= 5) {


             spawnGoat();
            GoatonWorld.numGoats++;
            timeBetweenSpawn = (GoatonWorld.Random.nextFloat() *
                    (maxTime - minTime) + minTime);
            spawnPerRound = (GoatonWorld.Random.nextInt(maxSpawn - minSpawn) *
                    + minSpawn);

//            System.out.println("tbs: " + timeBetweenSpawn);
//            System.out.println("spr: " + spawnPerRound);
        }
    }
    @Override
    public String getID() {
        return "GoatSpawnerComponent";
    }

    private void spawnGoat()
    {
        GameObject go = GameObject.Instantiate(GoatFactory.Create());
        go.create();
        go.setPosition(gameObject.getPosition());

        float rand = GoatonWorld.Random.nextFloat();
        if (rand > 0.75f) {
            go.getBody().setLinearVelocity(-10f, 0f);
        } else if (rand > 0.5f) {
            go.getBody().setLinearVelocity(10f, 0f);
        } else if (rand > 0.25f) {
            go.getBody().setLinearVelocity(0f, 8f);
        } else {
            go.getBody().setLinearVelocity(0f, -8f);
        }

//        go.getBody().setLinearVelocity((GoatonWorld.Random.nextFloat()-0.5f)*20,(GoatonWorld.Random.nextFloat()-0.5f)*20) ;
 //       go.send(new Message("pickup"));
        go.send(new Message("throw"));
    }
}
