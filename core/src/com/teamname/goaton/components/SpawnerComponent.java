package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Prefabs.PitFactory;

/**
 * Created by pya on 1/31/16.
 */
public class SpawnerComponent extends Component {
    private float timeBetweenSpawn;
    protected float minTime = 1f;
    protected float maxTime = 2f;

    private float spawnPerRound;
    protected int minSpawn = 1;
    protected int maxSpawn = 10;

    GameObject go;

    public SpawnerComponent() {}

    @Override
    public void create() {
        timeBetweenSpawn = (GoatonWorld.Random.nextFloat() *
                (maxTime - minTime) + minTime);

//        System.out.println("tbs: " + timeBetweenSpawn);
        spawnPerRound = (GoatonWorld.Random.nextInt(maxSpawn - minSpawn) *
                + minSpawn);
//        System.out.println("spr: " + spawnPerRound);
    }

    @Override
    public void update(float dt) {
        timeBetweenSpawn -= dt;
        if (timeBetweenSpawn < 0) {

            for (int numPits = 0; numPits < spawnPerRound; numPits++) {
                GameObject.Instantiate(go);
            }

            timeBetweenSpawn = (GoatonWorld.Random.nextFloat() *
                    (maxTime - minTime) + minTime);
            spawnPerRound = (GoatonWorld.Random.nextInt(maxSpawn - minSpawn) *
                     + minSpawn);

//            System.out.println("tbs: " + timeBetweenSpawn);
//            System.out.println("spr: " + spawnPerRound);
        }
    }

    @Override
    public Component cloneComponent() {
        return new SpawnerComponent();
    }

    @Override
    public String getID() {
        return "SpawnerComponent";
    }

}
