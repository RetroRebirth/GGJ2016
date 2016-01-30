package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kpidding on 1/30/16.
 */
public class Scene {
    /*public static final int BG_LAYER = 0;
    public static final int ENTITY_LAYER = 1;
    public static final int GOAT_LAYER = 2;
    public static final int NUM_LAYERS = 3;*/

    private float timer = 0;
    List<GameObject> objects = new ArrayList<GameObject>();
    List<GameObject>[] layers;
    //Queue<GameObject>[] addList;
    Queue<GameObject> addList = new LinkedList<GameObject>();
    public Scene ()
    {
        /*
        layers = new ArrayList[3];
        addList = new LinkedList[3];
        for (int i = 0; i < NUM_LAYERS; i++)
        {
            layers[i] = new ArrayList<GameObject>();
            addList[i] = new LinkedList<GameObject>();
        }*/

    }
    public void addObject(GameObject object)
    {
        //addList[ENTITY_LAYER].add(object);
        addList.add(object);
    }
    /*
    public void addObject(GameObject object, int layer)
    {
        addList[layer].add(object);
    }*/
    public void sendGlobalMessage(Message msg)
    {
        for(GameObject obj : objects)
        {
            obj.send(msg);
        }
    }
    public void updateRender(float dt, SpriteBatch sb, Camera camera)
    {
        /*for (int i = 0; i < NUM_LAYERS; i++)
        {
            while (!addList[i].isEmpty())
            {
                GameObject obj = addList[i].remove();
                obj.create();
                objects.add(obj);
                layers[i].add(obj);
            }
        }*/
        //doing physics here?

        while (!addList.isEmpty())
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

        GoatonWorld.world.step(dt, 6, 2);
        Array<Body> bodies = new Array<Body>();
        GoatonWorld.world.getBodies(bodies);
        for(Body b : bodies)
        {
            GameObject go = (GameObject)b.getUserData();
            go.position = b.getPosition();
        }


    }

    public void updatePhysics(float dt) {
        float frameTime = Math.min(dt, 0.5f);

    }
    protected void doCollision()
    {
        /*for(int i = 0; i < NUM_LAYERS; i++)
        {
            for (GameObject obj : layers[i])
            {
                for (GameObject other : layers[i])
                {
                    if (obj != other) //not self
                    {
                        if (obj.position.dst(other.position) < obj.radius + other.radius)
                        {
                            //collision happens

                            if (obj.position.dst(other.position) < Math.max(obj.radius, other.radius) - Math.min(obj.radius, other.radius))
                            {
                                //object is inside the other
                            }
                        }
                    }
                }
            }
        }*/
    }


    public void create()
    {
        /*for(int i = 0; i < NUM_LAYERS; i++)
        {*/
            while (!addList.isEmpty())
            {
                objects.add(addList.remove());
            }
            for (GameObject obj : objects)
            {
                obj.create();
            }
        //}
    }

}
