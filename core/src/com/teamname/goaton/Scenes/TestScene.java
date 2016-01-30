package com.teamname.goaton.Scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Prefabs.GoatFactory;
import com.teamname.goaton.Prefabs.PitFactory;
import com.teamname.goaton.Prefabs.PlayerFactory;
import com.teamname.goaton.Scene;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by kpidding on 1/30/16.
 */
public class TestScene extends Scene {
    private GameObject player;
    private GameObject createPlayer()
    {
        GameObject player = PlayerFactory.Create();
        return player;
    }

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
                go.setPosition(b.getPosition());
            }

            //debugRenderer.render(GoatonWorld.world, camera.combined);
    }

    @Override
    public void create() {
        this.player = createPlayer();
        addObject(this.player);
        createGoats();
        createPits();
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

        for (int i = 0; i < 50; i++) {
            GameObject newPit = GameObject.Instantiate(pit);
        }

    }
}
