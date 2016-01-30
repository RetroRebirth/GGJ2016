package com.teamname.goaton.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.Input.KeyboardInputSource;
import com.teamname.goaton.Scene;
import com.teamname.goaton.components.PlayerMovementComponent;
import com.teamname.goaton.components.ShooterComponent;
import com.teamname.goaton.components.SpriteRenderComponent;

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
                new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        GameObject testSprite = new GameObject();

        testSprite.addComponent(new SpriteRenderComponent(
                new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        player.addComponent(new ShooterComponent(testSprite));

        return player;
    }

    public TestScene()
    {
        this.player = createPlayer();
        addObject(this.player);
    }
}
