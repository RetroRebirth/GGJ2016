package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Assets;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;
import com.teamname.goaton.components.GoatMovementComponent;
import com.teamname.goaton.components.GoatPhysicsComponent;
import com.teamname.goaton.components.GoatSpriteComponent;

import java.util.Random;

/**
 * Created by pya on 1/30/16.
 */
public class Goat extends GameObject {
    public Goat() {
        super();
        this.addComponent(new GoatMovementComponent());
        this.addComponent(new GoatSpriteComponent(new Sprite(new Texture(Gdx.files.internal(Assets.goat)))));
        this.addComponent(new GoatPhysicsComponent());

        Random random = new Random();
        float boundedHeight = random.nextFloat() * GoatonWorld.worldHeight;
        float boundedWidth = random.nextFloat() * GoatonWorld.worldWidth;

        System.out.println(boundedHeight);
        System.out.println(boundedWidth);

        this.position = new Vector2(boundedWidth, boundedHeight);
    }
}
