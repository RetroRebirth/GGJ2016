package com.teamname.goaton.components;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.Prefabs.DemonFactory;
import com.teamname.goaton.Prefabs.PitFactory;

/**
 * Created by pya on 1/31/16.
 */
public class DemonSpawnerComponent extends SpawnerComponent {
    Vector2 pitPos;

    public DemonSpawnerComponent(Vector2 pitPos) {
        this.pitPos = pitPos;
        go = DemonFactory.Create(pitPos);
        minTime = 5f;
        maxTime = 10f;

        maxSpawn = 2;
    }

    @Override
    public Component cloneComponent() {
        return new DemonSpawnerComponent(pitPos);
    }

    @Override
    public String getID() {
        return "DemonSpawnerComponent";
    }
}
