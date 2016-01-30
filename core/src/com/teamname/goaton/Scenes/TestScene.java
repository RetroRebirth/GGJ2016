package com.teamname.goaton.Scenes;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Prefabs.GoatFactory;
import com.teamname.goaton.Prefabs.Player;
import com.teamname.goaton.Scene;
import com.teamname.goaton.GoatonWorld;

/**
 * Created by kpidding on 1/30/16.
 */
public class TestScene extends Scene {
    private GameObject player;
    private GameObject createPlayer()
    {
        GameObject player = new Player();
        return player;
    }

    public TestScene()
    {

    }

    @Override
    public void create() {
        this.player = createPlayer();
        addObject(this.player);
        createGoats();
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
}
