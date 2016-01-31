package com.teamname.goaton.Scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Prefabs.*;
import com.teamname.goaton.Scene;
import com.teamname.goaton.components.BoundsPhysicsComponent;
import com.teamname.goaton.components.GoatSpawnerComponent;

/**
 * Created by kpidding on 1/30/16.
 */
public class TestScene extends Scene {





    public TestScene()
    {
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
        camera.position.set(player.getScreenPosition().x, player.getScreenPosition().y, 0);
        camera.update();
        super.updateRender(dt,sb);
            //debugRenderer.render(GoatonWorld.world, camera.combined);
    }

    @Override
    public void create() {

        //createGoats();
        //createPits();
        //createPitSpawner();
        createGoatSpawner();
        addObject(DemonBossFactory.Create());
        super.create();

    }

    private void createGoats() {
        GameObject goat = GoatFactory.Create();


        for(int i = 0; i < 100; i++)
        {
            GameObject newGoat = GameObject.Instantiate(goat);
            /*newGoat.position.x = GoatonWorld.Random.nextFloat()* 500;
            newGoat.position.y = GoatonWorld.Random.nextFloat()* 500;*/
        }
    }

    private void createPits() {
        GameObject pit = PitFactory.Create();
        GameObject.Instantiate(DemonFactory.Create());
        for (int i = 0; i < 50; i++) {
            GameObject newPit = GameObject.Instantiate(pit);
        }

    }

    private void createPitSpawner() {
        GameObject.Instantiate(PitSpawnerFactory.Create());
    }

    private void createGoatSpawner() {
        GameObject go = new GameObject();
        go.addComponent(new GoatSpawnerComponent());
        GameObject.Instantiate(go);
    }
}
