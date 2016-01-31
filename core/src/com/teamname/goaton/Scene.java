package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kpidding on 1/30/16.
 */
public abstract class Scene {
    /*public static final int BG_LAYER = 0;
    public static final int ENTITY_LAYER = 1;
    public static final int GOAT_LAYER = 2;
    public static final int NUM_LAYERS = 3;*/

    private float timer = 0;
    protected List<GameObject> objects = new ArrayList<GameObject>();
    protected List<GameObject>[] layers;
    //Queue<GameObject>[] addList;
    protected Queue<GameObject> addList = new LinkedList<GameObject>();
    protected Queue<GameObject> removeList = new LinkedList<GameObject>();
    protected Viewport viewport;

    protected Camera camera;
    protected float viewportScale = 1.0f;



    protected Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();


    public Scene ()
    {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1280,720,camera);
        viewport.apply();

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

    private void finishRemove()
    {
        while(!removeList.isEmpty())
        {
            objects.remove(removeList.remove());
        }
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

    public void updateRender(float dt, SpriteBatch sb) {
        GoatonWorld.world.step(dt, 6, 2);
        Array<Body> bodies = new Array<Body>();
        GoatonWorld.world.getBodies(bodies);
        for (Body b : bodies) {
            GameObject go = (GameObject) b.getUserData();
            go.setPosition(b.getPosition());
        }

        while (!addList.isEmpty()) {
            GameObject obj = addList.remove();
            obj.create();
            objects.add(obj);
        }

        for (GameObject obj : objects) {
            obj.update(dt);
            obj.render(sb);
        }

        //After all is said and done, finish the remove.
        finishRemove();
        //debugRenderer.render(GoatonWorld.world, camera.combined);
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

    public Camera getCamera() {
        return camera;
    }

    public void updateViewport(int width, int height)
    {
        viewport.update(width,height);
    }

    public void removeObject(GameObject other) {
        removeList.add(other);
    }
}
