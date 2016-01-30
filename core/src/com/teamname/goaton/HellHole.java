package com.teamname.goaton;

import com.badlogic.gdx.graphics.Color;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pya on 1/30/16.
 */
public class HellHole extends GameObject {
    private int radius;
    public ArrayList<Color> GoatsWanted;

    public HellHole(HashMap<String, Component> comps) {
        components = new HashMap<String, Component>(comps);

        GoatRequests();
    }

    public void GoatRequests() {
        /* Creates GoatsWanted Array */
    }

    private ArrayList<Color> genRandColor(int num) {
        /* Generate random array of colors (goat requests) */
        return null;
    }



}
