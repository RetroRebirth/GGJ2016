package com.teamname.goaton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kpidding on 1/29/16.
 */
public class World {
    private static Scene scene;

    public static void addObject(GameObject object)
    {
        scene.addObject(object);
    }
    public static void sendGlobalMessage(Message msg)
    {
        scene.sendGlobalMessage(msg);
    }
    public static void updateRender(float dt, SpriteBatch sb)
    {
       scene.updateRender(dt, sb);
    }
    public static void setScene(Scene newScene)
    {
        newScene.create();
        World.scene = newScene;
    }
}
