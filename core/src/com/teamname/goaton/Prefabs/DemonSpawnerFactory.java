package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.DemonSpawnerComponent;

/**
 * Created by pya on 1/31/16.
 */
public class DemonSpawnerFactory {
    public static GameObject Create(Vector2 pitPos) {
        GameObject go = new GameObject();
        go.addComponent(new DemonSpawnerComponent(pitPos));

        return go;
    }
}
