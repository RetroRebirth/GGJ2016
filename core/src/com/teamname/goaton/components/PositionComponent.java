package com.teamname.goaton.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.teamname.goaton.Component;
import com.teamname.goaton.GoatonWorld;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by pya on 1/30/16.
 */
public class PositionComponent extends Component {
    protected float boundedWidth;
    protected float boundedHeight;

    public PositionComponent() {}


    @Override
    protected void create() {
        int numSpawns = GoatonWorld.SpawnAreas.size();
        Rectangle chosen = GoatonWorld.SpawnAreas.get(GoatonWorld.Random.nextInt(numSpawns));

        float upBoundX = chosen.getX() + chosen.getWidth();
        float upBoundY = chosen.getY() + chosen.getHeight();
        float lowBoundX = chosen.getX();
        float lowBoundY = chosen.getY();

        boundedWidth =  GoatonWorld.Random.nextFloat() * (upBoundX - lowBoundX) + lowBoundX;
        boundedHeight = GoatonWorld.Random.nextFloat() * (upBoundY - lowBoundY) + lowBoundY;

        gameObject.setPosition(new Vector2(boundedWidth, boundedHeight));
    }

    @Override
    public Component cloneComponent() {
        return new PositionComponent();
    }

    @Override
    public String getID() {
        return "PositionComponent";
    }
}
