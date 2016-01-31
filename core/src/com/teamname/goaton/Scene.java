package com.teamname.goaton;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamname.goaton.Prefabs.BoundBoxFactory;
import com.teamname.goaton.Prefabs.DemonBossFactory;
import com.teamname.goaton.Prefabs.PlayerFactory;
import com.teamname.goaton.Prefabs.PlayerTriggerFactory;
import com.teamname.goaton.components.GoatSpawnerComponent;
import com.teamname.goaton.components.GoatStackComponent;

import java.util.*;

/**
 * Created by kpidding on 1/30/16.
 */
public abstract class Scene {
    /*public static final int BG_LAYER = 0;
    public static final int ENTITY_LAYER = 1;
    public static final int GOAT_LAYER = 2;
    public static final int NUM_LAYERS = 3;*/
    public static GameObject Player;
    private float timer = 0;

    protected List<GameObject> objects = new LinkedList<GameObject>();

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
            GameObject go = removeList.remove();
            go.destory();
            objects.remove(go);
            GoatonWorld.world.destroyBody(go.getBody());
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
        objects.sort(new Comparator<GameObject>() {
            @Override
            public int compare(GameObject o1, GameObject o2) {
                int cmp = o1.layer - o2.layer;
                if(cmp != 0)
                {
                    return cmp;
                }
                return (int)o2.getScreenPosition().y - (int)o1.getScreenPosition().y;
            }
        });

        for (GameObject obj : objects) {
            obj.update(dt);
            obj.render(sb);
        }

        //After all is said and done, finish the remove.
        finishRemove();
        //OrthographicCamera camera2 = new OrthographicCamera(200,200);
        //debugRenderer.render(GoatonWorld.world, camera2.combined);
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
        other.send(new Message("destroy"));
        removeList.add(other);
    }

    public void addMapObjects(TiledMap tileMap) {
        for (MapLayer l : tileMap.getLayers())
        {
            if(l.getName().equals("WallLayer"))
            {
                for(MapObject mo : l.getObjects())
                {
                    mo.setVisible(false);
                    addObject(BoundBoxFactory.Create(mo,true));
                }
            }
            else if(l.getName().equals("BoundaryBarrier"))
            {
                for(MapObject mo : l.getObjects())
                {
                    mo.setVisible(false);
                    addObject(BoundBoxFactory.Create(mo,false));
                }
            }
            else if(l.getName().equals("Actors"))
            {

                MapObjects objects = l.getObjects();
                Vector2 lArmVec;
                MapObject lArm = objects.get("leftArm");
                lArmVec = new Vector2((Float)lArm.getProperties().get("x")+ 25,(Float)lArm.getProperties().get("y") + 200);

                Vector2 rArmVec;
                MapObject rArm = objects.get("rightArm");
                rArmVec = new Vector2((Float)rArm.getProperties().get("x") + 25,(Float)rArm.getProperties().get("y") + 200);

                GameObject boss = DemonBossFactory.Create(lArmVec,rArmVec);
                boss.setPosition(MapObjectToWorld(objects.get("BossSpawn")));
                addObject(boss);

                Scene.Player = PlayerFactory.Create();
                Scene.Player.setPosition(MapObjectToWorld(objects.get("PlayerSpawn")));
                addObject(Scene.Player);

                addObject(PlayerTriggerFactory.Create(objects.get("BossTrigger"),new Message("spawnBoss"),true));

                GameObject goatSpawner = new GameObject();
                goatSpawner.addComponent(new GoatSpawnerComponent());
                goatSpawner.setPosition(MapObjectToWorld(objects.get("GoatPit")));
                addObject(goatSpawner);

            }
            else if (l.getName().equals("SpawnArea"))
            {
                GoatonWorld.GoatSpawnAreas = new ArrayList<Rectangle>();
                GoatonWorld.PitSpawnAreas = new ArrayList<Rectangle>();

                for(MapObject mo : l.getObjects())
                {
                    MapProperties props = mo.getProperties();
                    Vector2 newVec = MapObjectToWorld(mo);
                    float wd = (Float)props.get("width")/GoatonWorld.TILE_SIZE;
                    float ht = (Float)props.get("height")/GoatonWorld.TILE_SIZE;

                    GoatonWorld.GoatSpawnAreas.add(boundingBox(newVec, wd, ht));
                    GoatonWorld.PitSpawnAreas.add(boundingBox(newVec, wd, ht));
                }
            }
        }
    }

    private Vector2 MapObjectToWorld(MapObject mo) {
        MapProperties props = mo.getProperties();
        System.out.println("MapProps :" + props);
        return new Vector2((Float)props.get("x")/GoatonWorld.TILE_SIZE, (Float)props.get("y")/GoatonWorld.TILE_SIZE);
    }

    private Rectangle boundingBox(Vector2 topLeft, float width, float height) {
        Rectangle rect = new Rectangle();
        float botLeftX = topLeft.x;
        float botLeftY = topLeft.y;

        rect.set(botLeftX, botLeftY, width, height);

        return rect;
    }
}
