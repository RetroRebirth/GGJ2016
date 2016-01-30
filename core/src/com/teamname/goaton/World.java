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
    static List<GameObject> objects = new ArrayList<GameObject>();
    static Queue<GameObject> addList = new LinkedList<GameObject>();
    public static void addObject(GameObject object)
    {
        addList.add(object);
    }
    public static void sendGlobalMessage(Message msg)
    {
        for(GameObject obj : objects)
        {
            obj.send(msg);
        }
    }
    public static void updateRender(float dt, SpriteBatch sb)
    {
        while(!addList.isEmpty())
        {
            objects.add(addList.remove());
        }

        for(GameObject obj : objects)
        {
            obj.update(dt);
            obj.render(sb);
        }
    }
}
