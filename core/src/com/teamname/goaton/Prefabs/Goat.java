package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.components.GoatMovementComponent;
import com.teamname.goaton.components.GoatPhysicsComponent;
import com.teamname.goaton.components.GoatSpriteComponent;

import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class Goat extends GameObject {
    public Goat() {
        super();
        this.addComponent(new GoatMovementComponent());
        this.addComponent(new GoatSpriteComponent(new Sprite(new Texture(Gdx.files.internal("art/goat.png")))));
        this.addComponent(new GoatPhysicsComponent());
    }
}
