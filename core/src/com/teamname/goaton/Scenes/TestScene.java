package com.teamname.goaton.Scenes;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.Input.ControllerInputSorce;
import com.teamname.goaton.Input.GameInputSource;
import com.teamname.goaton.Input.KeyboardInputSource;
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
    //ViewPort guiViewport;
    private  GameObject gui;
    private SpriteBatch guiSB;
    private BitmapFont font;
    GameInputSource src;

    private final float TUTORIAL_DUR = 5f;
    private final float TUTORIAL_DELAY = 15f;

    private float tutorialDelay = TUTORIAL_DELAY;
    private float tutorialDuration = TUTORIAL_DUR;
    private boolean displayTutorial = true;

    private boolean runUpdate = true;
    @Override
    public void sendGlobalMessage(Message msg) {
        gui.send(msg);
        if(msg.equals("gameFinish"))
        {
            runUpdate = false;
        }

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
        gui.addComponent(new PlayerHealthComponent());
        gui.addComponent(new CreditsComponent());

        gui.create();
        guiSB = new SpriteBatch();

        if(Controllers.getControllers().size > 0)
        {
            src = new ControllerInputSorce(Controllers.getControllers().get(0));
        }
        else
        {
            src = new KeyboardInputSource();
        }
    }

    @Override
    public void updateRender(float dt, SpriteBatch sb) {
        if (displayTutorial && src.isThrowButtonPressed()) {
            displayTutorial = false;
        }

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
        if(runUpdate)
        {
            super.updateRender(dt, sb);
        }
        sb.end();

        //viewport.apply();
        guiSB.setProjectionMatrix(guiCamera.combined);
        guiSB.begin();
        gui.update(dt);
        gui.render(guiSB);

        boolean dead = ((PlayerMovementComponent) Scene.Player.getComponent("PlayerMovementComponent")).dead;
        if (displayTutorial && !dead) {
            if (tutorialDelay > 0f) {
                tutorialDelay -= dt;
            } else if (tutorialDuration > 0f) {
                tutorialDuration -= dt;
                renderTutorialTextToScreen();
            } else {
                tutorialDelay = TUTORIAL_DELAY;
                tutorialDuration = TUTORIAL_DUR;
            }
        }

        if (dead) {
            renderGameOverTextToScreen();
        }

        guiSB.end();

        debugRenderer.render(GoatonWorld.world, camera.combined);
    }

    private void renderTutorialTextToScreen() {
        font.draw(guiSB, "Press [SPACE] to pick up goats!", -100, 48);
    }

    private void renderGameOverTextToScreen() {
        font.draw(guiSB, "GAME OVER", -46, 48);
    }

    @Override
    public void create() {

        //createGoats();
        //createPits();
        //createPitSpawner();
        createSound();
        createPits();

        font = new BitmapFont();
        font.setColor(0.73f, 0.03f, 0.03f, 1.0f); // bright-red

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

    private void createSound(){
        GameObject go = new GameObject();

        go.addComponent(new SoundComponent());

        GameObject.Instantiate(go);
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
