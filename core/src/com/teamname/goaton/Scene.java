package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kpidding on 1/30/16.
 */
public class Scene {
     List<GameObject> objects = new ArrayList<GameObject>();
     Queue<GameObject> addList = new LinkedList<GameObject>();
    public void addObject(GameObject object)
    {
        addList.add(object);
    }
    public void sendGlobalMessage(Message msg)
    {
        for(GameObject obj : objects)
        {
            obj.send(msg);
        }
    }
    public void updateRender(float dt, SpriteBatch sb, Camera camera)
    {
        while(!addList.isEmpty())
        {
            GameObject obj = addList.remove();
            obj.create();
            objects.add(obj);

        }

        for(GameObject obj : objects)
        {
            obj.update(dt);
            obj.render(sb);
        }

    }
    protected void doCollision()
    {



    }

    public void create()
    {
        while(!addList.isEmpty())
        {
            objects.add(addList.remove());
        }
        for(GameObject obj : objects)
        {
            obj.create();
        }
    }

}
