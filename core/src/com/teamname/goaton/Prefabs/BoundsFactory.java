package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.BoundsPhysicsComponent;

/**
 * Created by pya on 1/31/16.
 */
public class BoundsFactory {
    public static GameObject CreateChain(Vector2[] vertices) {
        return createBarrier(vertices, false);
    }

    public static GameObject CreateLoop(Vector2[] vertices) {
        return createBarrier(vertices, true);
    }

    private static GameObject createBarrier(Vector2[] vertices, boolean isLoop) {
        GameObject go = new GameObject();

        go.addComponent(new BoundsPhysicsComponent(vertices, isLoop));
        return go;
    }

}
