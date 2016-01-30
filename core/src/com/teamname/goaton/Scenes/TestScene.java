package com.teamname.goaton.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Input.KeyboardInputSource;
import com.teamname.goaton.Scene;
import com.teamname.goaton.World;
import com.teamname.goaton.components.*;

/**
 * Created by kpidding on 1/30/16.
 */
public class TestScene extends Scene {
    private GameObject player;
    private GameObject createPlayer()
    {
        GameObject player = new GameObject();
        player.addComponent(new PlayerMovementComponent(new KeyboardInputSource()));
        player.addComponent(new SpriteRenderComponent(
                new Sprite(new Texture(Gdx.files.internal("art/player.png")))));
        GameObject testSprite = new GameObject();


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
        GameObject goat = new GameObject();
        goat.addComponent(new GoatMovementComponent());
        goat.addComponent(new GoatSpriteComponent(new Sprite(new Texture(Gdx.files.internal("art/goat.png")))));

        for(int i = 0; i < 100; i++)
        {
            GameObject newGoat = GameObject.Instantiate(goat);
            newGoat.position.x = World.Random.nextFloat()* 500;
            newGoat.position.y = World.Random.nextFloat()* 500;

        }
    }
}
