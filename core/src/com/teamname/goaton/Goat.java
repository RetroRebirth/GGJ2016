package com.teamname.goaton;

import com.badlogic.gdx.graphics.Color;

import com.teamname.goaton.Component;
import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class Goat extends GameObject {
    /* com.teamname.goaton.Goat color */
    public Color color;

    public Goat(HashMap<String, Component> comps, Color color) {
        components = new HashMap<String, Component>(comps);

        this.color = color;
    }


    public void update() {

    }

    public void wander() {

    }

    public void runAway() {

    }
}
