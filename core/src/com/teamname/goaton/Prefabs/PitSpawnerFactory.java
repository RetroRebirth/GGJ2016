package com.teamname.goaton.Prefabs;

import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.PitSpawnerComponent;
import com.teamname.goaton.components.SpawnerComponent;

/**
 * Created by pya on 1/31/16.
 */
public class PitSpawnerFactory {

    public static GameObject Create() {
        GameObject go = new GameObject();

        go.addComponent(new PitSpawnerComponent());
        return go;
    }
}
