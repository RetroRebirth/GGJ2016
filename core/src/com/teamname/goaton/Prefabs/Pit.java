package com.teamname.goaton.Prefabs;

import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.GameObject;
import com.teamname.goaton.GoatonWorld;

import java.util.Random;

/**
 * Created by pya on 1/30/16.
 */
public class Pit {
    public Pit() {



        Random random = new Random();
        float boundedHeight = random.nextFloat() * GoatonWorld.worldHeight;
        float boundedWidth = random.nextFloat() * GoatonWorld.worldWidth;

        this.position = new Vector2(boundedWidth, boundedHeight);
    }


}
