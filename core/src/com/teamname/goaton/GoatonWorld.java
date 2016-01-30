package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

/**
 * Created by kpidding on 1/29/16.
 */
public class GoatonWorld {
    public static World world = new World(new Vector2(0, 0), true); //no gravity
    private static Scene scene;
    public static Random Random = new Random();

    public static <T extends Enum<?>> T RandomEnum(Class<T> clazz){
        int x = GoatonWorld.Random.nextInt(clazz.getEnumConstants().length);
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
        GoatonWorld.scene = newScene;
        newScene.create();
    }

}
