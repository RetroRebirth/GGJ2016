package com.teamname.goaton.components;

import com.teamname.goaton.Component;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Prefabs.PitFactory;

/**
 * Created by pya on 1/31/16.
 */
public class PitSpawnerComponent extends Component {
    private float timeBetweenSpawn;
    private float minTime = 1f;
    private float maxTime = 2f;

    private float spawnPerRound;
    private int minSpawn = 1;
    private int maxSpawn = 10;

    GameObject pit = PitFactory.Create();

    public PitSpawnerComponent() {}

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
                GameObject.Instantiate(pit);
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
        return new PitSpawnerComponent();
    }

    @Override
    public String getID() {
        return "PitSpawnerComponent";
    }

}
