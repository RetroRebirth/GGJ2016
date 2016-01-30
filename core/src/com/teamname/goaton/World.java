package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamname.goaton.components.GoatMovementComponent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by kpidding on 1/29/16.
 */
public class World {
    private static Scene scene;
    public static Random Random = new Random();

    public static <T extends Enum<?>> T RandomEnum(Class<T> clazz){
        int x = World.Random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static void addObject(GameObject object)
    {
        scene.addObject(object);
    }
    public static void sendGlobalMessage(Message msg)
    {
        scene.sendGlobalMessage(msg);
    }
    public static void updateRender(float dt, SpriteBatch sb, Camera camera)
    {
       scene.updateRender(dt, sb, camera);
    }
    public static void setScene(Scene newScene)
    {
        World.scene = newScene;
        newScene.create();

    }

}
