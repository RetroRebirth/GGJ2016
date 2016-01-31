package com.teamname.goaton.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sun.prism.image.ViewPort;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Message;
import com.teamname.goaton.Prefabs.*;
import com.teamname.goaton.Scene;
import com.teamname.goaton.components.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class TestScene extends Scene {

    private CameraControlComponent camControl;
    private OrthographicCamera guiCamera;
    ViewPort guiViewport;
    private  GameObject gui;
    private SpriteBatch guiSB;

    @Override
    public void sendGlobalMessage(Message msg) {
        gui.send(msg);
        super.sendGlobalMessage(msg);
    }

    public TestScene()
    {
        camControl = new CameraControlComponent(camera);
        GameObject camObj = new GameObject();
        camObj.addComponent(camControl);
        addObject(camObj);
        guiCamera = new OrthographicCamera(1280/2,720/2);
        //guiCamera.position.set(-1280/4,720/4,0);
        guiCamera.zoom = 1.f;
        guiCamera.update();
        //viewport = new StretchViewport(1280/2,720/2,guiCamera);
        //guiCamera.position.set(1280 / 4.0f, 720 / 4.0f, 0);

        gui = new GameObject();
        gui.addComponent(new GUIBossHealthComponent());
        gui.create();
        guiSB = new SpriteBatch();

    }

    @Override
    public void updateRender(float dt, SpriteBatch sb) {
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
        camControl.setCameraPosition(new Vector2(Scene.Player.getScreenPosition().x, Scene.Player.getScreenPosition().y));
        super.updateRender(dt, sb);
        sb.end();

        //viewport.apply();
        guiSB.setProjectionMatrix(guiCamera.combined);
        guiSB.begin();
        gui.update(dt);
        gui.render(guiSB);
        guiSB.end();

            //debugRenderer.render(GoatonWorld.world, camera.combined);
    }

    @Override
    public void create() {

        //createGoats();
        //createPits();
        //createPitSpawner();

        createPits();

        super.create();

    }

//    private void createGoats() {
//        GameObject goat = GoatFactory.Create();
//
//
//        for(int i = 0; i < 100; i++)
//        {
//            GameObject newGoat = GameObject.Instantiate(goat);
//            /*newGoat.position.x = GoatonWorld.Random.nextFloat()* 500;
//            newGoat.position.y = GoatonWorld.Random.nextFloat()* 500;*/
//        }
//    }

    private void createPits() {
//        GameObject pit = PitFactory.Create();
        //GameObject.Instantiate(DemonFactory.Create());
        //for (int i = 0; i < 50; i++) {

        GameObject.Instantiate(PitFactory.Create());
        GameObject.Instantiate(PitFactory.Create());
        GameObject.Instantiate(PitFactory.Create());

        //}

    }

//    private void createPitSpawner() {
//        GameObject.Instantiate(PitSpawnerFactory.Create());
//    }
//
//    private void createGoatSpawner() {
//        GameObject go = new GameObject();
//        go.addComponent(new GoatSpawnerComponent());
//
//        GameObject.Instantiate(go);
//    }
}
